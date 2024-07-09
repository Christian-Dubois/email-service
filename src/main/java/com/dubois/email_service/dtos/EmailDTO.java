package com.dubois.email_service.dtos;


import java.io.Serializable;
import java.util.Objects;


public record EmailDTO(String destination, String subject, String body) {
}
