package com.filrouge.designflow.spring.service;

import com.filrouge.designflow.spring.model.NotificationEmail;
import com.filrouge.designflow.spring.exceptions.SpringFlowException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

//for log
@Slf4j
class MailService {


    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

//  Method for sending mail from (my email) to user
    @Async
    void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("charifah1112@gmail.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new SpringFlowException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
    }

}