package com.zhy.spring.application.mail.test;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

public class AliMailChannel {


    private static final String SMTP_HOST = "smtp.mxhichina.com";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    private static final String MAIL_USER = "notice@xxxx.com";
    private static final String MAIL_PASSWORD = "xxxx";


    public String send(MailMessage mailMessage) {
        try {

            //设置SSL连接、邮件环境
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            Properties props = System.getProperties();
            //协议
            //props.setProperty("mail.transport.protocol", "smtp");

            props.setProperty("mail.smtp.host", SMTP_HOST);//smtp服务器地址
            //props.setProperty("mail.smtp.port", "25");//非加密端口
            // 使用ssl加密方式，进行如下配置：
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", "465");

            props.setProperty("mail.smtp.auth", "true");//表示SMTP发送邮件，需要进行身份验证
            props.setProperty("mail.smtp.from", MAIL_USER);//mailfrom 参数
            props.setProperty("mail.user", MAIL_USER);//发件人的账号
            props.setProperty("mail.password", MAIL_PASSWORD);// 发件人的账号的密码，如果开启三方客户端安全密码请使用新生产的密码
            //建立邮件会话
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                //身份认证
                protected PasswordAuthentication getPasswordAuthentication() {
                    //发件人的账号、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            });
            //建立邮件对象
            MimeMessage message = new MimeMessage(session);
            //设置邮件的发件人
            InternetAddress from = new InternetAddress(MAIL_USER, "tickets bay"); //from 参数,可实现代发，注意：代发容易被收信方拒信或进入垃圾箱。
            message.setFrom(from);
            //设置邮件的收件人
            String[] to = {mailMessage.getToMail()};//收件人列表
            InternetAddress[] sendTo = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                //System.out.println("发送到：" + to[i]);
                sendTo[i] = new InternetAddress(to[i]);
            }

            //传入收件人
            message.setRecipients(Message.RecipientType.TO, sendTo);
            //设置邮件的主题
            message.setSubject(mailMessage.getSubject());
            //设置邮件的文本
            String content = mailMessage.getMessage();
            message.setContent(content, "text/html;charset=UTF-8");
            //设置时间
            message.setSentDate(new Date());
            message.saveChanges();
            //发送邮件
            Transport.send(message);

            return "success";
        } catch (Exception e) {
            //log.error("send mail error {}", mailMessage, e);

            return null;
        }
    }


    public static void main(String[] args) throws IOException {
        AliMailChannel aliMailChannel = new AliMailChannel();

        MailMessage mailMessage = new MailMessage();
        mailMessage.setToMail("1111111@qq.com");
        mailMessage.setSubject("通知");

        Path path = Paths.get("D:\\资料\\票牛\\需求文档\\港澳台计划1.0\\test.html");
        byte[] bytes = Files.readAllBytes(path);

        String content = new String(bytes);
        mailMessage.setMessage(content);

        aliMailChannel.send(mailMessage);
    }

}
