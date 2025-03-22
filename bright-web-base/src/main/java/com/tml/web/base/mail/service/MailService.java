package com.tml.web.base.mail.service;

import com.tml.web.base.mail.dto.MailDto;
import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


/**
 *  通用的发送邮件服务
 * @author JacksonTu
 * @date 2020/4/2 10:38
 */
@Slf4j
@Component
public class MailService {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private Environment env;

    /**
     * 发送简单文本文件
     */
    @Async
    public void sendSimpleEmail(final MailDto dto) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(env.getProperty("spring.mail.username"));
            message.setTo(dto.getTos());
            message.setSubject(dto.getSubject());
            message.setText(dto.getContent());
            mailSender.send(message);
            log.info("发送简单文本文件-发送成功!");
        } catch (Exception e) {
            log.error("发送简单文本文件-发生异常： ", e.fillInStackTrace());
        }
    }

    /**
     * 发送花哨邮件
     *
     * @param dto
     */
    @Async
    public void sendHTMLMail(final MailDto dto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
            messageHelper.setFrom(env.getProperty("spring.mail.username"));
            messageHelper.setTo(dto.getTos());
            messageHelper.setSubject(dto.getSubject());
            messageHelper.setText(dto.getContent(), true);
            mailSender.send(message);
            log.info("发送花哨邮件-发送成功!");
        } catch (Exception e) {
            log.error("发送花哨邮件-发生异常： ", e.fillInStackTrace());
        }
    }
}
