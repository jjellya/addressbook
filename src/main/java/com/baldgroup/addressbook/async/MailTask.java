package com.baldgroup.addressbook.async;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;

/**
 * Create By  @林俊杰
 * 2020/4/21 20:08
 *
 * @version 1.0
 */
public class MailTask implements Runnable {
    private String code;

    private String email;
    private String mailFrom;
    private String domainName;
    private JavaMailSenderImpl javaMailSender;

    public MailTask(String code, String email, String mailFrom, String domainName, JavaMailSenderImpl javaMailSender) {
        this.code = code;
        this.email = email;
        this.mailFrom = mailFrom;
        this.domainName = domainName;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void run() {
        //发送邮件
        javaMailSender.send(new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                System.out.println("开始发送邮件");
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
                mimeMessageHelper.setFrom(mailFrom);
                mimeMessageHelper.setTo(email);
                mimeMessageHelper.setSubject("来自addressbook的一封激活邮件");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<html><head></head><body>"
                        +"您好，感谢您的注册，这是一封验证邮件，请");
                stringBuilder.append("<a href=" + domainName + "/activate?code=");
                stringBuilder.append(code);
                stringBuilder.append(">点击即可完成激活</a>，感谢您的支持！</body>");
                mimeMessageHelper.setText(stringBuilder.toString(), true);
                System.out.println("发送结束");




            }
        });

    }
}
