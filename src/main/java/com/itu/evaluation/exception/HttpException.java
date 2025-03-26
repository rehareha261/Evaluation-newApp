package com.itu.evaluation.exception;

import java.util.Map;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpException extends Exception {
    private Map<String, Object> error;

    public String get(String key) {
        if (this.error != null && this.error.containsKey(key)) {
            return this.error.get(key).toString();
        }
        return "No message available";
    }

    public HttpException(Exception e) {
        super(e);
        System.out.println(e.getMessage());
        if (e instanceof HttpClientErrorException) {
            HttpClientErrorException clientError = (HttpClientErrorException) e;
            String responseBody = clientError.getResponseBodyAsString();
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> errorResponse = objectMapper.readValue(responseBody, Map.class);

                this.error = (Map<String, Object>) errorResponse.get("errors");

            } catch (Exception jsonException) {
                System.err.println("Erreur lors du parsing du JSON: " + jsonException.getMessage());
            }
        }
        //Decommente si code 500
        // if (e instanceof HttpServerErrorException) {
        //     HttpServerErrorException clientError = (HttpServerErrorException) e;
        //     String responseBody = clientError.getResponseBodyAsString();
        //     System.out.println(responseBody+"ldknvijndvjsndji");
        //     try {
        //         ObjectMapper objectMapper = new ObjectMapper();
        //         Map<String, Object> errorResponse = objectMapper.readValue(responseBody, Map.class);

        //         this.error = (Map<String, Object>) errorResponse.get("errors");

        //     } catch (Exception jsonException) {
        //         System.err.println("Erreur lors du parsing du JSON: " + jsonException.getMessage());
        //     }
        // }
    }
}
