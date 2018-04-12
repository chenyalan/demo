package com.andlinks.mybatis.utils;

import javafx.util.Pair;
import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;


import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

import java.util.Map;

/**
 * Created by 陈亚兰 on 2018/2/26.
 */
@Component
public class JavaMailUtils {
    @Value("${mail.from}")
    private  String from;

    @Autowired
    private  JavaMailSender mailSender;

    public  void sendSimpleMail(String sendTo, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(sendTo);
        message.setSubject(title);
        message.setSentDate(new Date());
        message.setText("<html><head></head><body><h1>hello!!zhangfl</h1></body></html>");
        mailSender.send(message);
    }

    //附件版本
    public void sendFuJian(String sendTo,String title,String content){
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(sendTo);
            helper.setSubject(title);
            helper.setText("这是附件");

            FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
            helper.addAttachment("附件-1.jpg", file);
            helper.addAttachment("附件-2.jpg", file);
        }catch (Exception e){
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }

    //velocity模板
    public void sendVelocity(String sendTo,String title,Map<String,Object> model){
        VelocityEngine velocityEngine =new VelocityEngine();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(sendTo);
            helper.setSubject(title);
            String text = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, "/src/main/resources/templates/template.vm", "UTF-8", model);
            helper.setText(text, true);
            mailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
