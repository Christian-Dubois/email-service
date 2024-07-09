package com.dubois.email_service.processor;


import com.dubois.email_service.dtos.EmailDTO;


public interface EmailSenderProcessor {

    void sendEmail(EmailDTO emailDTO);

}
