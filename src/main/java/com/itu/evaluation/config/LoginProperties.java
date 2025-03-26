package com.itu.evaluation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class LoginProperties {
    @Value("${login.client_id}")
    private String clientId;
    @Value("${login.client_secret}")
    private String clientSecret;
    @Value("${login.grant_type}")
    private String grant;
    @Value("${login.scope}")
    private String scope;    
}
