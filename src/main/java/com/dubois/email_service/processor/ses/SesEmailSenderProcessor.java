package com.dubois.email_service.processor.ses;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.dubois.email_service.exceptions.EmailServiceException;
import com.dubois.email_service.dtos.EmailDTO;
import com.dubois.email_service.processor.EmailSenderProcessor;


@Service
public class SesEmailSenderProcessor implements EmailSenderProcessor {

    @Value("${aws.emailSource}")
    private String EMAIL;

    @Autowired
    private AmazonSimpleEmailService amazonSimpleEmailService;

    @Override
    public void sendEmail(EmailDTO emailDTO) {
        SendEmailRequest request = this.buildRequest(emailDTO);
        this.sendEmail(request);
    }

    private SendEmailRequest buildRequest(EmailDTO emailDTO) {
        Destination destination = new Destination().withToAddresses(emailDTO.destination());
        Content subject = new Content(emailDTO.subject());
        Body body = new Body().withText(new Content(emailDTO.body()));
        Message message = new Message() //
                .withSubject(subject) //
                .withBody(body);

        return new SendEmailRequest() //
                .withSource(EMAIL) //
                .withDestination(destination) //
                .withMessage(message);
    }

    private void sendEmail(SendEmailRequest request) {
        try {
            this.amazonSimpleEmailService.sendEmail(request);
        } catch (Exception e) {
            throw new EmailServiceException("Failure while sending email", e);
        }
    }
}
