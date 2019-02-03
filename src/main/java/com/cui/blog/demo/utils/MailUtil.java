package com.cui.blog.demo.utils;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

    //邮件配置文件
    private static final String PROPERTIES_PATH = "classpath:mail.properties";

    /**
     *读取邮箱配置
     */
    public static final String SMTPSERVER = Utils.getProperties(PROPERTIES_PATH,"SMTPSERVER");
    public static final String SMTPPORT = Utils.getProperties(PROPERTIES_PATH,"SMTPPORT");
    public static final String SIMPLEACCOUNT = Utils.getProperties(PROPERTIES_PATH,"SIMPLEACCOUNT");
    public static final String AUTHCODE = Utils.getProperties(PROPERTIES_PATH,"AUTHCODE");
    public static final String ACCOUT = Utils.getProperties(PROPERTIES_PATH,"ACCOUT");
    public static final String PWD = Utils.getProperties(PROPERTIES_PATH,"PWD");


    public static void SendEmail(String subject,String text,String reciveAddress) throws Exception {

        // 创建邮件配置
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", SMTPSERVER); // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.port", SMTPPORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
        props.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl

        // 创建验证器
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                // 密码验证
                return new PasswordAuthentication(SIMPLEACCOUNT, AUTHCODE);

            }
        };


        // 根据邮件配置创建会话，注意session别导错包
        Session session = Session.getDefaultInstance(props,auth);
        // 开启debug模式，可以看到更多详细的输入日志
        session.setDebug(true);
        //创建邮件
        MimeMessage message = createEmail(session,subject,text,reciveAddress);
        //获取传输通道
        Transport transport = session.getTransport();
        transport.connect(SMTPSERVER,ACCOUT, PWD);
        //连接，并发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

    }


    public static  MimeMessage createEmail(Session session,String subject,String text,String reciveAddress) throws Exception {
        // 根据会话创建邮件
        MimeMessage msg = new MimeMessage(session);
        // address邮件地址, personal邮件昵称, charset编码方式
        InternetAddress fromAddress = new InternetAddress(ACCOUT,
                "Blog", "utf-8");
        // 设置发送邮件方
        msg.setFrom(fromAddress);
        InternetAddress receiveAddress = new InternetAddress(
                reciveAddress, "test", "utf-8");
        // 设置邮件接收方
        msg.setRecipient(RecipientType.TO, receiveAddress);
        // 设置邮件标题
        msg.setSubject(subject, "utf-8");
        msg.setText(text);
        // 设置显示的发件时间
        msg.setSentDate(new Date());
        // 保存设置
        msg.saveChanges();
        return msg;
    }
}