package com.itu.evaluation.Util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperExtension {
    

    public static String extractErrorMessage(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            return rootNode.path("errors").path("message").asText();
        } catch (Exception e) {
            return "Erreur lors du parsing JSON: " + e.getMessage();
        }
    }

}
