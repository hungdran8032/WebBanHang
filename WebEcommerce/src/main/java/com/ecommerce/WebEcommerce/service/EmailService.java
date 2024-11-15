package com.ecommerce.WebEcommerce.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void sendVerificationOtpEmail(String userEmail, String otp, String subject, String text) throws MessagingException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setText(text);


            javaMailSender.send(message);
        }catch (MailException e){
            throw new MailSendException("Không gửi đến email này được");
        }
    }
}
