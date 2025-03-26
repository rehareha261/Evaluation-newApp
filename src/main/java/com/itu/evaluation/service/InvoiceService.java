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
import com.itu.evaluation.dto.Invoices;
import com.itu.evaluation.dto.InvoicesTotal;
import com.itu.evaluation.dto.Offer;

import jakarta.servlet.http.HttpSession;

@Service
public class InvoiceService {
    private final RestTemplate restTemplate;
    private final HeaderGen headerGen;
    private final HttpSession session;


    @Autowired
    public InvoiceService(RestTemplate restTemplate, HeaderGen headerGen, HttpSession session){
        this.restTemplate = restTemplate;
        this.headerGen = headerGen;
        this.session = session;
    }

    public Map<String, List<Invoices>> getInvoices() throws Exception {
        String url = ApiUrl.INVOICES_API_URL;

        ResponseEntity<ApiResponse<List<Invoices>>> response = restTemplate.exchange(
            url, HttpMethod.GET, 
            new HttpEntity<>(this.headerGen.generateHeader(this.session)), 
            new ParameterizedTypeReference<ApiResponse<List<Invoices>>>() {}
        );

        return response.getBody() != null ? response.getBody().getData() : null;
    }

    public Integer getCountInvoices()throws Exception{
        String url = ApiUrl.INVOICES_API_URL +"/count";

        ResponseEntity<ApiResponse<Integer>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(this.headerGen.generateHeader(this.session)), new ParameterizedTypeReference<ApiResponse<Integer>>() {});

        return response.getBody() != null ? response.getBody().getData().get("count") : null;
    }

    public Double getTotalInvoices()throws Exception{
        String url = ApiUrl.INVOICES_TOTAL_API_URL;

        ResponseEntity<ApiResponse<Double>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(this.headerGen.generateHeader(this.session)), new ParameterizedTypeReference<ApiResponse<Double>>() {});

        return response.getBody() != null ? response.getBody().getData().get("total") : null;
    }

    public List<InvoicesTotal> getTotalInvoicesDetail()throws Exception{
        String url = ApiUrl.INVOICES_API_URL_DETAILS;

        ResponseEntity<ApiResponse<List<InvoicesTotal>>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(this.headerGen.generateHeader(this.session)), new ParameterizedTypeReference<ApiResponse<List<InvoicesTotal>>>() {});

        return response.getBody() != null ? response.getBody().getData().get("invoices") : null;
    }


    public String setRemise(Double remise)throws Exception{
        String url = ApiUrl.INVOICES_API_URL_REMISE;
        
        Map<String, Double> requestBody = new HashMap<>();
        requestBody.put("remise", remise);

        HttpEntity<Map<String, Double>> entity = new HttpEntity<>(requestBody, this.headerGen.generateHeader(session));
            
        ResponseEntity<ApiResponse<String>> response = restTemplate.exchange(url, HttpMethod.PUT, entity, new ParameterizedTypeReference<ApiResponse<String>>() {});

        return response.getBody() != null ? response.getBody().getData().get("message") : null;
    }
}
