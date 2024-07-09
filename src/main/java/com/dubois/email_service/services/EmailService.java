package com.dubois.email_service.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dubois.email_service.processor.EmailSenderProcessor;
import com.dubois.email_service.dtos.EmailDTO;


@Service
public class EmailService {

    @Autowired
    private EmailSenderProcessor emailSenderProcessor;

    public void sendEmail(EmailDTO emailDTO) {
        this.emailSenderProcessor.sendEmail(emailDTO);
    }
}
