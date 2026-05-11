package com.jiumai.base.common.core.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/**
 * @version 1.0
 * commons-jexl-1.1.jar
 * commons-lang-2.4.jar
 */
@Component
public class BusiCodeGenerate  {
    private static final Log log = LogFactory
            .getLog(BusiCodeGenerate.class);

    private int timeOut = 2000;// 超时设置

    private static String LOCK_OBJ_CODE = "LOCK_OBJ_BUSI_CODE";
    
    private static Map<String,Map<String,String>> ruleMaps=new HashMap<String,Map<String,String>>();
    
    @Autowired
    private DataSource ds;
    
    
    public void setDataSource(DataSource ds)  throws Exception{
    	this.ds = ds;
    }
    
    
    /*public void setConnection(Connection cons){
    	this.conn=cons;
    }*/
    
    
    /**
     * 获取当前可用的业务编码 内置变量currentDay 当天日期 格式: yyyyMMdd
     * 
     * 
     * @param busiCodeRuleCode
     *            业务编码
     * @param paramMap
     *            参数列表信息
     */
    public synchronized String generateBusiCode(String busiCodeRuleCode, Map<String,Object> paramMap) {
        if (paramMap == null){
            paramMap = new Hashtable<>();
        }
//        log.info("传入的业务编码规则编码是" + busiCodeRuleCode);
//        Set<Map.Entry<String, Object>> entries = paramMap.entrySet();
//        for (Map.Entry<String, Object> entry : entries) {
//            String key = entry.getKey();
//            if (key == null){
//                continue;
//            }
//            log.info("传入的参数为key=" + key);
//            Object obj = paramMap.get(key);
//            log.info("传入的参数为value=" + obj);
//        }
//        log.info("传入的参数为" + (new ToStringBuilder(paramMap)).toString());
        if (StringUtils.isEmpty(busiCodeRuleCode)) {
            log.info("传入参数为空，不能编码....");
            return null;
        }
       
        // 调用业务代码生成线程生成业务代码
        final CodeGen codeGen = new CodeGen(busiCodeRuleCode, paramMap);
        new Thread() {
            public synchronized void run() {
                codeGen.gen();
            }
        }.start();
        return codeGen.get();

    }

    
 // 内部类，主要用于异步生成编码
    private class CodeGen {
        String result = "";

        String busiCodeRuleCode;

        Map<String,Object> paramMap;
        

        public CodeGen(String busiCodeRuleCode, Map<String,Object> paramMap) {
            this.busiCodeRuleCode = busiCodeRuleCode;
            this.paramMap = paramMap;
             
        }

        public Map<String,String> getBusiCodeRule(String busiRuleCode) throws Exception{
        	Map<String,String> result=ruleMaps.get(busiRuleCode);
        	if(result!=null){
        		return ruleMaps.get(busiRuleCode);
        	}else{
        		result=new HashMap<>();
        	}
        	PreparedStatement stmt=null;
        	ResultSet rs=null;
        	Connection conn = null;
			try {
				conn = ds.getConnection();
				stmt = conn.prepareStatement("select * from sso_busi_code_rule where BUSI_CODE_RULE_CODE =?");// .createStatement();
				stmt.setString(1, this.busiCodeRuleCode);
				rs = stmt.executeQuery();

				if (rs.next()) {
					ResultSetMetaData meta = rs.getMetaData();
					int count = meta.getColumnCount();
					for (int i = 1; i <= count; i++) {
						// meta.getColumnType(column)
						result.put(meta.getColumnName(i).toUpperCase(),
								rs.getString(i));
					}
					return result;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
                if(rs != null){
                    rs.close();
                }
                if(stmt != null){
                    stmt.close();
                }
                if(conn != null){
                    conn.close();
                }
			}
        	 
            return null;
        }
        
        
        public BusiCodeGen getBusiCodeGen(String gencode,long ruleId)throws Exception{
      	  String hql = "select BUSI_CODE_GEN_ID,BUSI_CODE_GEN_CODE,BUSI_CODE_RULE_ID,CURRENT_VALUE,NEXT_VALUE,UPDATE_DAY,UPDATE_OP,CREATE_DAY,CREATE_OP from sso_busi_code_gen bg where busi_Code_Gen_Code =?  and busi_Code_Rule_Id=?";
      	  ResultSet rs=null;
      	  PreparedStatement stmt=null;
      	  Connection conn = null;
		try {
			conn = ds.getConnection();
      	  	stmt=conn.prepareStatement(hql); 
            stmt.setString(1, gencode);
            stmt.setLong(2, ruleId);
            rs=stmt.executeQuery();
            
            if(rs.next()){
          	  BusiCodeGen gen=new BusiCodeGen();
          	  gen.busiCodeGenId=rs.getLong("BUSI_CODE_GEN_ID");
          	  gen.busiCodeGenCode=rs.getString("BUSI_CODE_GEN_CODE");
          	  gen.busiCodeRuleId=rs.getLong("BUSI_CODE_RULE_ID");
          	  gen.currentValue=rs.getString("CURRENT_VALUE");
          	  gen.nextValue=rs.getString("NEXT_VALUE");
          	  gen.updateDay=rs.getDate("UPDATE_DAY");
          	  gen.updateOp=rs.getString("UPDATE_OP");
          	  gen.createDay=rs.getDate("CREATE_DAY");
          	  gen.createOp=rs.getString("CREATE_OP");;
                return gen;
            	}
            	
      	  }catch(Exception e){
      		  e.printStackTrace();
      	  }finally{
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
      	  }
      	  return null;
            
        }
        
        public BusiCodeGen saveBusiCodeGen(BusiCodeGen gen,Connection conn)throws Exception{
        	  String insert = "insert into sso_busi_code_gen(" +
        	  		"BUSI_CODE_GEN_CODE,BUSI_CODE_RULE_ID,CURRENT_VALUE,NEXT_VALUE,UPDATE_DAY,UPDATE_OP,CREATE_DAY,CREATE_OP)" +
        	  		" values(?,?,?,?,?,?,?,?)";
        	  
        	  String update = "update  sso_busi_code_gen set " +
          	  		"BUSI_CODE_GEN_CODE =? ,BUSI_CODE_RULE_ID =? ,CURRENT_VALUE =? ,NEXT_VALUE =? ,UPDATE_DAY =? ,UPDATE_OP =? ,CREATE_DAY =? ,CREATE_OP =?  " +
          	  		" where BUSI_CODE_GEN_ID=? ";
        	  
        	  PreparedStatement stmt=null;
        	  try{
        	      if(gen.busiCodeGenId>0){
        	    	  stmt=conn.prepareStatement(update); 
        	    	  stmt.setString(1, gen.busiCodeGenCode);
        	    	  stmt.setLong(2, gen.busiCodeRuleId);
        	    	  stmt.setString(3, gen.currentValue);
        	    	  stmt.setString(4, gen.nextValue);
        	    	  stmt.setDate(5, new java.sql.Date(gen.updateDay.getTime()));
        	    	  stmt.setString(6, gen.updateOp);
        	    	  stmt.setDate(7, new java.sql.Date(gen.createDay.getTime()));
        	    	  stmt.setString(8, gen.createOp);
        	    	  stmt.setLong(9, gen.busiCodeGenId);
        	    	  stmt.executeUpdate();
        	      }else{
        	    	  stmt=conn.prepareStatement(insert); 
        	    	  stmt.setString(1, gen.busiCodeGenCode);
        	    	  stmt.setLong(2, gen.busiCodeRuleId);
        	    	  stmt.setString(3, gen.currentValue);
        	    	  stmt.setString(4, gen.nextValue);
        	    	  stmt.setDate(5, new java.sql.Date(gen.updateDay.getTime()));
        	    	  stmt.setString(6, gen.updateOp);
        	    	  stmt.setDate(7, new java.sql.Date(gen.createDay.getTime()));
        	    	  stmt.setString(8, gen.createOp);
        	    	  stmt.executeUpdate();
        	      }
              	
        	  }catch(Exception e){
        		  e.printStackTrace();
        	  }finally{
                  if(stmt != null){
                      stmt.close();
                  }
        	  }
        	  return null;
              
          }
        
        /**
         * 获取当前可用的业务编码 内置变量currentDay 当天日期 格式: yyyyMMdd
         */
        public synchronized void gen() {
             
        	Connection conn  = null;
        	
            try {
            	Map<String,String> busiCodeRule = getBusiCodeRule(busiCodeRuleCode);
                if (busiCodeRule == null) {
                	log.info("===根据业务规则编码" + busiCodeRuleCode + "没有找到业务规则对象,不能生成业务编码");
                    return;
                }
//                log.info("===>业务规则编码对象标识是" + busiCodeRule.get("BUSI_CODE_RULE_ID"));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String currentDay = sdf.format(new Date());
                paramMap.put("currentDay", currentDay);
                paramMap.put("yyyymmdd", currentDay);
                //ExpressionEvaluator exp = new ExpressionEvaluator();
                String prefix = busiCodeRule.get("PREFIX");
                if (prefix == null)
                    prefix = "";
                String suffix = busiCodeRule.get("SUFFIX");
                if (suffix == null)
                    suffix = "";
             // 替换前缀定义的变量
                prefix = ExpressionEvaluator.evaluate(prefix, paramMap);
             // 替换后缀定义的变量
                suffix = ExpressionEvaluator.evaluate(suffix, paramMap);
//                log.info("===>前后缀实例后数据为:" + prefix + "," + suffix);
                BusiCodeGen busiCodeGen = null;
                String busiCodeGenCode = "";
                int ruleType=Integer.parseInt(busiCodeRule.get("RULE_TYPE"));
                if (ruleType == 0) {// 仅规则
                    busiCodeGenCode = busiCodeRule.get("BUSI_CODE_RULE_CODE");
                }
                if (ruleType == 1) {// 前缀+规则
                    busiCodeGenCode = busiCodeRule.get("BUSI_CODE_RULE_CODE") + "_"
                            + prefix;
                }
                if (ruleType == 2) {// 规则+后缀
                    busiCodeGenCode = busiCodeRule.get("BUSI_CODE_RULE_CODE") + "_"
                            + suffix;
                }
                if (ruleType == 3) {// 前缀+规则+后缀
                    busiCodeGenCode = busiCodeRule.get("BUSI_CODE_RULE_CODE") + "_"
                            + prefix + "_" + suffix;
                }
                String currentValue = busiCodeRule.get("MIN_VALUE");
                if ((currentValue != null) && (currentValue.trim().equals("-1"))) {
                    result = prefix + suffix;
                    notify();
                    return;
                }
                 
                //Transaction tr = session.beginTransaction();
                //System.out.println(conn.getAutoCommit()+" "+conn.getTransactionIsolation()+" "+conn.getHoldability());
                //conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                conn = ds.getConnection();
                
                boolean autocommit=conn.getAutoCommit();
                if(autocommit){
                 conn.setAutoCommit(false);
                }
                Statement stm = null;
                ResultSet rs = null;
                try {
                    String hql = " select * from sso_lock_obj lo where lo.lock_Obj_Code='"
                            + LOCK_OBJ_CODE + "' for update";
                    stm=conn.createStatement();
                    rs=stm.executeQuery(hql);
                
                    if (!rs.next()){
                    	 log.info("===>没有找到编码标识为" + LOCK_OBJ_CODE
                                 + "的锁对象,不能生成业务编码");
                        notify();
                        return;
                    }
                        
                    busiCodeGen = getBusiCodeGen(busiCodeGenCode, Long.parseLong(busiCodeRule.get("BUSI_CODE_RULE_ID")));
                    if (busiCodeGen == null) {
                    	 log.info("===>业务规则生成编码对象没有找到，重新生成一个");
                        busiCodeGen = new BusiCodeGen();
                        busiCodeGen.busiCodeGenCode=busiCodeGenCode;
                        busiCodeGen.busiCodeRuleId=Long.parseLong(busiCodeRule.get("BUSI_CODE_RULE_ID"));
                        busiCodeGen.createDay=new Date();
                        busiCodeGen.updateDay=new Date();
                        busiCodeGen.currentValue=busiCodeRule.get("MIN_VALUE");
                        busiCodeGen.nextValue=busiCodeRule.get("MIN_VALUE");
                    } else {
//                        log.info("===>业务规则生成编码对象找到标识为"+ busiCodeGen.busiCodeGenId);
                    }
                    currentValue = busiCodeRule.get("MIN_VALUE");
                    busiCodeGen.currentValue=busiCodeGen.nextValue;
                    if (Integer.parseInt(busiCodeRule.get("IS_CYC_DAY")) == 1) {
//                        log.info("===>按天循环生成流水号");
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        if (busiCodeGen.updateDay == null)
                            busiCodeGen.updateDay=new Date();
                        try {
                            String curDateStr = sdf.format(new Date());
                            String updateDateStr = sdf.format(busiCodeGen.updateDay);
//                            log.info("===>当前时间为[" + curDateStr + "] 最后一次更新时间为["
//                                    + updateDateStr+"]");
                            Date date1 = sdf.parse(curDateStr);
                            Date date2 = sdf.parse(updateDateStr);
                            if (date1.after(date2)) {
                                busiCodeGen.currentValue=busiCodeRule.get("MIN_VALUE");
                                busiCodeGen.nextValue=busiCodeRule.get("MIN_VALUE");
                            }
                        } catch (Exception e) {
                        }
                    }
                    String wad = busiCodeRule.get("WAD_CHAR");
                    String nextValue = busiCodeGen.nextValue;
//                    log.info("===>当前处理循环日期后的下一个流水数据为" + nextValue);
                    if (Integer.parseInt(busiCodeRule.get("TYPE")) == 1) {
                        int next = Integer.parseInt(busiCodeGen.nextValue) + 1;
                        busiCodeGen.nextValue=String.valueOf(next);
                    } else if (Integer.parseInt(busiCodeRule.get("TYPE")) == 0) {
                        char[] chars = busiCodeGen.nextValue.toCharArray();
                        busiCodeGen.nextValue=String.valueOf(nextCharValue(chars, chars.length - 1));
                    }
                    busiCodeGen.updateDay=new Date();
                    if (busiCodeRule.get("NEED_WAD") !=null && Integer.parseInt(busiCodeRule.get("NEED_WAD")) == 1) {// 需要补字符
                        if (StringUtils.isEmpty(wad)) {// 如果需要补字符，但字符是空，则设置黙认字符
                            if (Integer.parseInt(busiCodeRule.get("TYPE")) == 1)
                                wad = "0";
                            else
                                wad = "A";
                        }
                        busiCodeRule.put("WAD_CHAR", wad); //  TODO   TODO
                        result = prefix
                                + StringUtils.leftPad(nextValue, busiCodeRule
                                        .get("MAX_VALUE").length(), wad) + suffix;
                    } else {
                        result = prefix + nextValue + suffix;
                    }
                    log.info("===>保存前生成的业务编码是" + result);
                    saveBusiCodeGen(busiCodeGen,conn);
                    //lockObj.setUpdateDate(new Date());
                } catch (Throwable e) {
                    log.error(e);
                    log.info("===>执行事务时出错");
                    conn.rollback();
                }finally {
                    if(rs != null){
                        rs.close();
                    }
                    stm.close();
                }
                conn.commit();
                conn.setAutoCommit(autocommit);
                
                
            } catch (Throwable e) {
                log.error(e);
                log.info("===>生成业务代码过程出现异常");
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
            notify();
        }

        synchronized public String get() {
            try {
                wait(timeOut);
            } catch (InterruptedException e) {
                log.info("===>等待处理时超时，不能生成业务编码");
            }
            return result;
        }

        /**
         * 根据字符串生成下一个字符
         * 
         * @param current
         *            当前字符串数组
         * @param index
         *            当前索引值
         * @return
         */
        private char[] nextCharValue(char[] current, int index) {
            if (current.length > 0) {
                if (current[index] == 'z' || current[index] == 'Z') {
                    current[index] = (char) ((int) current[index] - 25);
                    return (index == 0) ? current : nextCharValue(current,
                            index - 1);
                } else {
                    current[index] = (char) ((int) current[index] + 1);
                    return current;
                }
            } else {
                return "".toCharArray();
            }
        }
    }
    
    public class BusiCodeGen implements Serializable {
        private static final long serialVersionUID = 1L;
          long busiCodeGenId;
          long busiCodeRuleId;
          java.lang.String currentValue;
          java.lang.String nextValue;
          java.util.Date updateDay;
          java.lang.String updateOp;
          java.util.Date createDay;
          java.lang.String createOp;
          java.lang.String busiCodeGenCode;
    }

}

