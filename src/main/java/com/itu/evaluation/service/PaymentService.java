package com.itu.evaluation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.itu.evaluation.Util.ApiResponse;
import com.itu.evaluation.Util.HeaderGen;
import com.itu.evaluation.constante.ApiUrl;
import com.itu.evaluation.dto.Payment;

import jakarta.servlet.http.HttpSession;

@Service
public class PaymentService {
    private final RestTemplate restTemplate;
    private final HeaderGen headerGen;
    private final HttpSession session;

    @Autowired
    public PaymentService(RestTemplate restTemplate, HeaderGen headerGen, HttpSession session){
        this.restTemplate = restTemplate;
        this.headerGen = headerGen;
        this.session = session;
    }

    public List<Payment> getPayments() throws Exception {
        String url = ApiUrl.PAYMENTS_API_URL;

        ResponseEntity<ApiResponse<List<Payment>>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(this.headerGen.generateHeader(this.session)), new ParameterizedTypeReference<ApiResponse<List<Payment>>>() {});

        return response.getBody() != null ? response.getBody().getData().get("payments") : null;
    }

    public Double getTotalPayments()throws Exception{
        String url = ApiUrl.PAYMENTS_TOTAL_API_URL;

        ResponseEntity<ApiResponse<Double>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(this.headerGen.generateHeader(this.session)), new ParameterizedTypeReference<ApiResponse<Double>>() {});

        return response.getBody() != null ? response.getBody().getData().get("total") : null;
    }

    public String updatePaymentAmount(String externalId, Double amount)throws Exception{
        try{
            Map<String, Double> requestBody = new HashMap<>();
            requestBody.put("amount", amount);

            String url = ApiUrl.PAYMENTS_API_URL+"/"+externalId+"/update";

            HttpEntity<Map<String, Double>> entity = new HttpEntity<>(requestBody, this.headerGen.generateHeader(session));
            
            ResponseEntity<ApiResponse<String>> response = restTemplate.exchange(url, HttpMethod.PUT, entity, new ParameterizedTypeReference<ApiResponse<String>>() {});

            return response.getBody() != null ? response.getBody().getData().get("message") : null;
        }catch(Exception e){
            throw e;
        }
    }

    public String cancelPayment(String externalId)throws Exception{
        try{
            String url = ApiUrl.PAYMENTS_API_URL+"/"+externalId+"/delete";
            ResponseEntity<ApiResponse<String>> response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(this.headerGen.generateHeader(this.session)), new ParameterizedTypeReference<ApiResponse<String>>() {});

            return response.getBody() != null ? response.getBody().getData().get("message") : null;
        }catch(Exception e){
            throw e;
        }
    }

}
