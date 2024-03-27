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

    @GetMapping("html")
    public void html() {
        // 收信人
        String to = "2668989410@qq.com";

        // 发送邮件
        mailService.sendHtmlMail(to, "邮箱验证码", this.content);
    }


    String content = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "\n" +
            "<head>\n" +
            "<meta charset=\"UTF-8\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
            "<title>票牛網</title>\n" +
            "<style>\n" +
            "html body {\n" +
            "margin: 0;\n" +
            "background-color: #F6F8FA;\n" +
            "}\n" +
            "</style>\n" +
            "<style type=\"text/css\">\n" +
            "hr {\n" +
            "height: 1px;\n" +
            "background: #E2E4E9;\n" +
            "border: 0px;\n" +
            "margin: 22px 0px 30px;\n" +
            "}\n" +
            "a {\n" +
            "color: #004CC4;\n" +
            "margin: 0px;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<div style=\"max-width: 670px; margin: 0px auto; font-size: 14px; color: #383838;\">\n" +
            "<div style=\"width: 100%; padding: 40px 40px 24px;display: flex;justify-content: space-between;align-items: center;\">\n" +
            "<div style=\"width: 112px;height: 40px;\">\n" +
            "<!-- 替換LOGO -->\n" +
            "<img style=\"width: 100%;\" src=\"https://static.piaoniu.com/pc/img/logo.png\" alt=\"logo\"/>\n" +
            "</div>\n" +
            "<!-- 替換郵件類型 -->\n" +
            "<div style=\"font-size: 20px;font-weight: 500; color: #525866;\"><a href=\"https://piaoniu.com\">創建賬號</a></div>\n" +
            "</div>\n" +
            "<div style=\"width: 100%; padding: 40px; background:#fff; \">\n" +
            "<!-- 替換郵件標題 -->\n" +
            "<div style=\"font-weight: 500;font-size: 20px;color: #0A0D14;\">親愛的用户121****8087@qq.com</div>\n" +
            "<!-- 替換郵件內容 -->\n" +
            "<div style=\"margin-top: 12px;font-size: 16px;color: #525866;line-height: 32px;\">\n" +
            "<span>\n" +
            "您申請的重置密碼，驗證碼如下：\n" +
            "<br>991244\n" +
            "<br>請注意此驗證碼有效期為15分鐘。\n" +
            "<br>如有疑問歡迎致電票牛客服服務熱線400-099-8987或郵箱kefu@ipiaoniu.com聯絡，隨時恭候哦~\n" +
            "<br>\n" +
            "<br>票牛網\n" +
            "</span>\n" +
            "</div>\n" +
            "<!-- 替換服務內容 -->\n" +
            "<div style=\"width: 100%; display: flex;justify-content: space-between; align-items: center; flex-wrap: wrap;margin-top: 28px;\">\n" +
            "<!-- 100%真票 -->\n" +
            "<div style=\"width: 50%; display: flex;align-items: center;padding: 12px 0px;\">\n" +
            "<div style=\"width: 25px;height: 25px;\">\n" +
            "<!-- 替換icon -->\n" +
            "<img style=\"width: 100%;\" src=\"https://static.piaoniu.com/pc/home/img/icon-hot.png\" alt=\"icon\"/>\n" +
            "</div>\n" +
            "<div style=\"margin-left: 10px;\">\n" +
            "<!-- 替換文案 -->\n" +
            "<div style=\"font-weight: 600;font-size: 16px;color: #0A0D14;line-height: 24px;\">100%真票</div>\n" +
            "<div style=\"font-size: 12px;color: #525866;line-height: 14px;\">票品經平臺鑒定，假一賠三</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "<!-- 出票保障 -->\n" +
            "<div style=\"width: 50%; display: flex;align-items: center;padding: 12px 0px;\">\n" +
            "<div style=\"width: 25px;height: 25px;\">\n" +
            "<!-- 替換LOGO -->\n" +
            "<img style=\"width: 100%;\" src=\"https://static.piaoniu.com/pc/home/img/icon-hot.png\" alt=\"icon\"/>\n" +
            "</div>\n" +
            "<div style=\"margin-left: 10px;\">\n" +
            "<!-- 替換文案 -->\n" +
            "<div style=\"font-weight: 600;font-size: 16px;color: #0A0D14;line-height: 24px;\">出票保障</div>\n" +
            "<div style=\"font-size: 12px;color: #525866;line-height: 14px;\">保障用戶下單有票，無票退一賠一</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "<!-- 無憂配送 -->\n" +
            "<div style=\"width: 50%; display: flex;align-items: center;padding: 12px 0px;\">\n" +
            "<div style=\"width: 25px;height: 25px;\">\n" +
            "<!-- 替換LOGO -->\n" +
            "<img style=\"width: 100%;\" src=\"https://static.piaoniu.com/pc/home/img/icon-hot.png\" alt=\"icon\"/>\n" +
            "</div>\n" +
            "<div style=\"margin-left: 10px;\">\n" +
            "<!-- 替換文案 -->\n" +
            "<div style=\"font-weight: 600;font-size: 16px;color: #0A0D14;line-height: 24px;\">無憂配送</div>\n" +
            "<div style=\"font-size: 12px;color: #525866;line-height: 14px;\">多種取票配送方式，開演前安心取票</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "<!-- 專業售後 -->\n" +
            "<div style=\"width: 50%; display: flex;align-items: center;padding: 12px 0px;\">\n" +
            "<div style=\"width: 25px;height: 25px;\">\n" +
            "<!-- 替換LOGO -->\n" +
            "<img style=\"width: 100%;\" src=\"https://static.piaoniu.com/pc/home/img/icon-hot.png\" alt=\"icon\"/>\n" +
            "</div>\n" +
            "<div style=\"margin-left: 10px;\">\n" +
            "<!-- 替換文案 -->\n" +
            "<div style=\"font-weight: 600;font-size: 16px;color: #0A0D14;line-height: 24px;\">專業售後</div>\n" +
            "<div style=\"font-size: 12px;color: #525866;line-height: 14px;\">專業團隊解答售前售後問題</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "<hr>\n" +
            "<div style=\"13px;color: #525866;line-height: 32px;\">\n" +
            "<span>如有疑問歡迎致電票牛客服服務熱線400-099-8987或郵箱</span>\n" +
            "<a href=\"kefu@ipiaoniu.com\">kefu@ipiaoniu.com</a>\n" +
            "<span>聯絡，隨時恭候哦~</span>\n" +
            "</div>\n" +
            "</div>\n" +
            "</div>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
}
