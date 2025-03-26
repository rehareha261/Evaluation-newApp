package com.itu.evaluation.Util;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.itu.evaluation.constante.Constante;

import jakarta.servlet.http.HttpSession;

@Component
public class HeaderGen {

    public HttpHeaders generateHeader(HttpSession session) throws Exception{
        String accessToken = (String) session.getAttribute(Constante.ACCESS_TOKEN_KEY);

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(accessToken);

        return headers;
    }

}
