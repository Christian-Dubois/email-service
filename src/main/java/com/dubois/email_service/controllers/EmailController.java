package com.dubois.email_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dubois.email_service.services.EmailService;
import com.dubois.email_service.dtos.EmailDTO;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDTO) {
        try {
            this.emailService.sendEmail(emailDTO);
            return ResponseEntity.ok("Email successfully sent");
        } catch (Exception e) {
            return ResponseEntity //
                    .status(HttpStatus.INTERNAL_SERVER_ERROR) //
                    .body(e.getMessage());
        }
    }
}
