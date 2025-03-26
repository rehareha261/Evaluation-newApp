package com.itu.evaluation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/user")
@Controller
public class UtilisateurController {

    @ResponseBody
    @GetMapping("/admin")
    public String adminMethod(){
        return "Admin method";
    }
    @ResponseBody
    @GetMapping("/client")
    public String clientMethod(){
        return "Client method";
    }
    @ResponseBody
    @GetMapping("/manager")
    public String managerMethod(){
        return "Manager method";
    }
}
