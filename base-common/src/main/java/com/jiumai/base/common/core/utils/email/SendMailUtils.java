package com.jiumai.base.common.core.utils.email;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;
import com.jiumai.base.common.core.utils.ValidataUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;


/**
 * 邮件处理工具类
 * 
 * @author louis<83030246@qq.com>
 * @date 2018-02-12
 * @version 1.0
 */
public class SendMailUtils {

    private static SysLog log = SysLogFactory.getLogger(SendMailUtils.class);

    /**
     *  邮件服务器地址
     */
    public static String MAIL_SMTP_HOST = "mail.smtp.host";

    /**
     * 邮件服务器端口
     */
    public static String MAIL_SMTP_PORT = "mail.smtp.port";

    /**
     *  邮件是否权限验证
     */
    public static String MAIL_SMTP_AUTH = "mail.smtp.auth";

    /**
     * 邮件发送者邮箱地址
     */
    public static String MAIL_FROM_ACC = "mail.from.acc";

    /**
     * 邮件发送者邮箱密码
     */
    public static String MAIL_FROM_PWD = "mail.from.pwd";

    /**
     *  邮件发送者名称
     */
    public static String MAIL_FROM_NAME = "mail.from.name";

    /**
     *  邮件接收账户
     */
    public static String MAIL_TO_ACCS = "mail.to.accs";
    
    /**
     *  邮件抄送账户
     */
    public static String MAIL_CC_ACCS = "mail.cc.accs";
    
    /**
     *  邮件主题
     */
    public static String MAIL_SUBJECT = "mail.subject";

    /**
     *  邮件内容
     */
    public static String MAIL_BODY = "mail.body";

    /**
     *  邮件附件
     */
    public static String MAIL_ATTACHMENT = "mail.attachment";
    
    /**
     * 发送文本邮件
     * 
     * @param mailParams
     *            mail.smtp.host  邮件服务器地址 
     *            mail.smtp.port  服务器端口 
     *            mail.smtp.auth  是否鉴权 
     *            mail.from.acc   邮件发送人 
     *            mail.from.pwd   邮件发送人密码 
     *            mail.from.name  邮件发送人名称
     *            mail.to.accs    邮件接收人,多个接收人用;隔开
     *            mail.subject    邮件主题
     *            mail.body       邮件内容
     *            mail.attachment 邮件附件
     * 
     * @return 结果
     * @throws Exception
     */
    public static boolean sendTextMail(Map<String, ?> mailParams) throws Exception {
        return sendMail(mailParams, "text/html; charset=utf-8");
    }

    /**
     * 发送HTML邮件
     * 
     * @param mailParams
     *            mail.smtp.host  邮件服务器地址 
     *            mail.smtp.port  服务器端口 
     *            mail.smtp.auth  是否鉴权 
     *            mail.from.acc   邮件发送人 
     *            mail.from.pwd   邮件发送人密码 
     *            mail.from.name  邮件发送人名称
     *            mail.to.accs    邮件接收人,多个接收人用;隔开
     *            mail.subject    邮件主题
     *            mail.body       邮件内容
     *            mail.attachment 邮件附件
     * 
     * @return 结果
     * @throws Exception
     */
    public static boolean sendHtmlMail(Map<String, ?> mailParams) throws Exception {
        return sendMail(mailParams, "text/html; charset=utf-8");
    }

    /**
     * 发送邮件
     * 
     * @param mailParams
     * 
     * @param type 
     *              "text/text; charset=utf-8" 文件类型内容
     *              "text/html; charset=utf-8" HTML类型内容
     * @return
     * @throws Exception
     */
    private static boolean sendMail(Map<String, ?> mailParams, String type) throws Exception {
        if (!SendMailUtils.validataMailParams(mailParams)) {
            return false;
        }

        Properties props = new Properties();
        props.put(MAIL_SMTP_HOST, mailParams.get(MAIL_SMTP_HOST));
        props.put(MAIL_SMTP_PORT, mailParams.get(MAIL_SMTP_PORT));
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.trust", mailParams.get(MAIL_SMTP_HOST));
//        props.put("mail.smtp.socketFactory.fallback", "false");
//        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        try {
            // 根据邮件会话属性和密码验证器构造一个发送邮件的session
            Session session = null;
            
            // 判断服务器是否需要身份验证
            Object isAuth = mailParams.get(MAIL_SMTP_AUTH);
            if (isAuth == null || StringUtils.equals((String) isAuth, "true") ) {
                props.put(MAIL_SMTP_AUTH, "true");
                SmtpAuth smtpAuth = new SmtpAuth((String) mailParams.get(MAIL_FROM_ACC), 
                        (String) mailParams.get(MAIL_FROM_PWD));
                session = Session.getDefaultInstance(props, smtpAuth);
            } else {
                props.put(MAIL_SMTP_AUTH, "false");
                session = Session.getDefaultInstance(props, null);
            }

            // 根据session创建一个邮件消息
            MimeMessage msg = new MimeMessage(session);
            // 设置邮件消息发送的时间
            msg.setSentDate(new Date());

            // 创建邮件发送者地址
            InternetAddress fromAddress = new InternetAddress((String) mailParams.get(MAIL_FROM_ACC),
                    mailParams.get(MAIL_FROM_NAME) == null ? null : (String) mailParams.get(MAIL_FROM_NAME), "UTF-8");
            msg.setFrom(fromAddress);

            // 创建邮件的接收者地址，并设置到邮件消息中(多个收件人)
            String[] mailTos = ((String) mailParams.get(MAIL_TO_ACCS)).split(";");
            InternetAddress[] toAddress = new InternetAddress[mailTos.length];
            for (int i = 0; i < mailTos.length; i++) {
        		toAddress[i] = new InternetAddress(mailTos[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, toAddress);
            
            // 邮件抄送地址，并设置到邮件消息中(多个人)
            String[] mailCcs = {};
            if(mailParams.get(MAIL_CC_ACCS) != null) {
            	mailCcs = ((String) mailParams.get(MAIL_CC_ACCS)).split(";");
            }
            InternetAddress[] ccAddress = new InternetAddress[mailCcs.length];
            for (int i = 0; i < mailCcs.length; i++) {
                if(StringUtils.isEmpty(mailCcs[i])){
                    ccAddress = null;
                }else {
                    ccAddress[i] = new InternetAddress(mailCcs[i]);
                }
            }
            msg.setRecipients(Message.RecipientType.CC, ccAddress);

            // 设置邮件消息的主题
            msg.setSubject(mailParams.get(MAIL_SUBJECT) == null ? null : (String) mailParams.get(MAIL_SUBJECT),
                    "UTF-8");

            // 设置邮件正文
            Multipart mainPart = new MimeMultipart();
            
            // 文本正文
            BodyPart textBody = new MimeBodyPart();
            textBody.setContent((String) mailParams.get(MAIL_BODY), type);
            mainPart.addBodyPart(textBody);
            
            // 添加邮件附件正文
            if (mailParams.get(MAIL_ATTACHMENT) != null) {
                File[] attachments = (File[]) mailParams.get(MAIL_ATTACHMENT);
                for (File attachment : attachments) {
                    BodyPart attachmentBody = new MimeBodyPart();
                    DataSource source = new FileDataSource(attachment);
                    attachmentBody.setDataHandler(new DataHandler(source));

                    // MimeUtility.encodeWord可以避免文件名乱码
                    attachmentBody.setFileName(MimeUtility.encodeWord(attachment.getName()));
                    mainPart.addBodyPart(attachmentBody);
                }
            }

            msg.setContent(mainPart); //Multipart加入到信件
            
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            transport.connect((String) mailParams.get(MAIL_SMTP_HOST), (String) mailParams.get(MAIL_FROM_ACC),
                    (String) mailParams.get(MAIL_FROM_PWD));

            msg.saveChanges();
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch(AuthenticationFailedException e1) {
            log.error("邮件身份验证失败", e1);
            throw e1;
        } catch (MessagingException e2) {
            log.error("邮件发送失败", e2);
            throw e2;
        }

        return true;
    }
    
    /**
     * 验证发送邮件参数,为空参数设置默认值
     * 
     * @param mailParams
     * @return
     */
    private static boolean validataMailParams(Map<String, ?> mailParams)
            throws IllegalArgumentException, ClassCastException {
        // 验证邮件服务器地址
        String mailSmtpHost = (String) mailParams.get(MAIL_SMTP_HOST);
        if (StringUtils.isEmpty(mailSmtpHost)) {
            throw new IllegalArgumentException("邮件服务器地址不可为空");
        }

        // 验证邮件服务器端口
        String mailSmtpPort = (String) mailParams.get(MAIL_SMTP_PORT);
        if (StringUtils.isEmpty(mailSmtpPort)) {
            throw new IllegalArgumentException("邮件服务器端口不可为空");
        }
        if (NumberUtils.toInt(mailSmtpPort, -1) <= 0) {
            throw new IllegalArgumentException("邮件服务器端口参数错误");
        }

        // 验证邮件是否需要参数验证
        String mailSmtpAuth = (String) mailParams.get(MAIL_SMTP_AUTH);
        if (StringUtils.isNotEmpty(mailSmtpAuth)
                && (!StringUtils.equals(mailSmtpAuth, "true") && !StringUtils.equals(mailSmtpAuth, "false"))) {
            throw new IllegalArgumentException("邮件是否需要参数验证参数错误");
        }

        // 验证邮件发送账户
        String mailFromAcc = (String) mailParams.get(MAIL_FROM_ACC);
        if (StringUtils.isEmpty(mailFromAcc)) {
            throw new IllegalArgumentException("邮件发送账号不可为空");
        }
        if (!ValidataUtils.Email(mailFromAcc)) {
            throw new IllegalArgumentException("邮件发送账号参数错误");
        }

        // 验证邮件发送密码
        String mailFromPwd = (String) mailParams.get(MAIL_FROM_PWD);
        if (StringUtils.isEmpty(mailFromPwd)) {
            throw new IllegalArgumentException("邮件发送密码不可为空");
        }

        // 验证邮件接收账户
        String mailToAccs = (String) mailParams.get(MAIL_TO_ACCS);
        if (StringUtils.isEmpty(mailToAccs)) {
            throw new IllegalArgumentException("邮件接受账户不可为空");
        }
        String[] mailToAcc = mailToAccs.split(";");
        for (String acc : mailToAcc) {
            if (!ValidataUtils.Email(acc)) {
                throw new IllegalArgumentException("邮件接受账号参数错误");
            }
        }

        // 验证邮件正文
        String mailBody = (String) mailParams.get(MAIL_BODY);
        if (StringUtils.isEmpty(mailBody)) {
            throw new IllegalArgumentException("邮件正文不可为空");
        }
        
        return true;
    }
    
    /**
     * 邮件权限验证封装
     * 
     */
    private static class SmtpAuth extends javax.mail.Authenticator {
        
        // 邮件发送人账号
        private String fromAcc;
        
        // 邮件发送人密码
        private String fromPwd;
        
        public SmtpAuth(String fromAcc, String fromPwd) {
            this.fromAcc = fromAcc;
            this.fromPwd = fromPwd;
        }
        
        protected PasswordAuthentication getPasswordAuthentication() {   
            return new javax.mail.PasswordAuthentication(fromAcc, fromPwd);   
        }
    }
}
