package com.itu.evaluation.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itu.evaluation.service.DashboardService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    private DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    // public String getMensuelle(RedirectAttributes model){
    //     Map<String, Object> data = this.dashboardService.getMensuelle();
    //     model.addAttribute("data", data);
    //     return "redirect:/admin/dashboard";
    // }
}
