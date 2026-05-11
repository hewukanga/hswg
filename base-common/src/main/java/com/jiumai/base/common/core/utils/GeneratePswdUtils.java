package com.jiumai.base.common.core.utils;

/**
 * 随机密码生成器
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
public class GeneratePswdUtils {

    // 密码内容
    private static final String[] pwdContentArr;

    static {
        StringBuilder pwdCon = new StringBuilder();
        pwdCon.append("a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
        pwdCon.append(",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z");
        pwdCon.append(",~,@,#,$,%,^,&,*,(,),_,+,|,`,.");
        pwdCon.append(",1,2,3,4,5,6,7,8,9,0");
        pwdContentArr = pwdCon.toString().split(",");
    }

    /**
     * 生成随机密码
     * 
     * @return
     */
    public static String getPswd(int length) {
        StringBuilder b = new StringBuilder();
        java.util.Random r;
        int k;
        for (int i = 0; i < length; i++) {
            r = new java.util.Random();
            k = r.nextInt();
            b.append(String.valueOf(pwdContentArr[Math.abs(k % 76)]));
        }
        return b.toString();
    }
}
