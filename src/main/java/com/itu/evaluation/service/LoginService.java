package com.itu.evaluation.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.itu.evaluation.Util.ApiResponse;
import com.itu.evaluation.config.LoginProperties;
import com.itu.evaluation.constante.ApiUrl;
import com.itu.evaluation.constante.Constante;
import com.itu.evaluation.exception.HttpException;

import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {    
    private final RestTemplate restTemplate;
    private final LoginProperties loginProperties;

    public LoginService(RestTemplate restTemplate, LoginProperties loginProperties){
        this.restTemplate = restTemplate;
        this.loginProperties = loginProperties;
    }

    public ResponseEntity<ApiResponse<String>> login(String email, String password, HttpSession session)throws Exception{
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        try{
            ResponseEntity<ApiResponse<String>> response = restTemplate.exchange(
                ApiUrl.LOGIN_API_URL, 
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ApiResponse<String>>() {}
            );
            return response;
        } catch (Exception e) {
            throw e;
        }
    }
}
