

package com.itu.evaluation.service;

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
import com.itu.evaluation.dto.Offer;
import com.itu.evaluation.dto.Offers;

import jakarta.servlet.http.HttpSession;

@Service
public class OfferService {
    private final RestTemplate restTemplate;
    private final HeaderGen headerGen;
    private final HttpSession session;

    @Autowired
    public OfferService(RestTemplate restTemplate, HeaderGen headerGen, HttpSession session){
        this.restTemplate = restTemplate;
        this.headerGen = headerGen;
        this.session = session;
    }

    public Map<String, List<Offer>> getOffers() throws Exception {
        String url = ApiUrl.OFFERS_API_URL + "/details";

        ResponseEntity<ApiResponse<List<Offer>>> response = restTemplate.exchange(
            url, HttpMethod.GET, 
            new HttpEntity<>(this.headerGen.generateHeader(this.session)), 
            new ParameterizedTypeReference<ApiResponse<List<Offer>>>() {}
        );
        return response.getBody() != null ? response.getBody().getData() : null;
    }

    public Integer getCountOffers()throws Exception{
        String url = ApiUrl.OFFERS_API_URL +"/count";

        ResponseEntity<ApiResponse<Integer>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(this.headerGen.generateHeader(this.session)), new ParameterizedTypeReference<ApiResponse<Integer>>() {});

        return response.getBody() != null ? response.getBody().getData().get("count") : null;
    }


}
