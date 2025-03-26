package com.itu.evaluation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itu.evaluation.Util.ApiResponse;
import com.itu.evaluation.constante.Constante;
import com.itu.evaluation.exception.HttpException;
import com.itu.evaluation.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping
    public String loginForm(){
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model){
        try {
            ResponseEntity<ApiResponse<String>> response = this.loginService.login(email, password, session);
            if (response.getStatusCode().is2xxSuccessful()) {
                ApiResponse<String> responseBody = response.getBody();
                System.out.println(responseBody);
                if (responseBody != null) {
                    String accessToken =  responseBody.getData().get(Constante.ACCESS_TOKEN_KEY);

                    session.setAttribute(Constante.ACCESS_TOKEN_KEY, accessToken);
                    session.setAttribute("email", email);

                    System.out.println("Access Token: " + accessToken);

                    return "redirect:/admin/dashboard";
                }
            }
        } catch (Exception e) {
            HttpException exception = new HttpException(e);
            model.addAttribute("message", exception.get("message"));
            System.out.println(exception.get("message"));
            return "login";
        }
        return "login";

    }
}
