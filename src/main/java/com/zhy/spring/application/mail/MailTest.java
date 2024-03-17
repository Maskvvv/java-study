package com.zhy.spring.application.mail;

import cn.hutool.core.util.CreditCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author zhouhongyin
 * @since 2024/3/17 17:38
 */
@RequestMapping("mail")
@RestController
public class MailTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("send")
    public void send() {
        doSend("1429855087@qq.com",
                "2668989410@qq.com",
                "我是标题",
                "我是内容我是内容我是内容我是内容我是内容我是内容我是内容");
    }


    public void doSend(String from, String to, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 发件人
        simpleMailMessage.setFrom(from);
        // 收件人
        simpleMailMessage.setTo(to);
        // 邮件主题
        simpleMailMessage.setSubject(subject);
        // 邮件内容
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }


    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping("sendTemplate")
    public void sendTemplate() {
        // 收信人
        String to = "2668989410@qq.com";
        // 邮件消息内容
        String message = "111详情：您正在尝试进行登录操作，若非是您本人的行为，请忽略!";
        // 随机生成验证码
        String code = CreditCodeUtil.randomCreditCode();

        // 设置邮件内容
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("code", code);
        String mail = templateEngine.process("mailtemplate.html", context);

        // 发送邮件
        mailService.sendHtmlMail(to, "邮箱验证码", mail);
    }


}
