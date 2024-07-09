package com.dubois.email_service.processor.ses;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;


@Configuration
public class AwsSesConfig {

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        return AmazonSimpleEmailServiceClientBuilder //
                .standard() //
                .withCredentials(new AWSStaticCredentialsProvider(credentials)) //
                .withRegion(this.awsRegion) //
                .build();
    }

}
