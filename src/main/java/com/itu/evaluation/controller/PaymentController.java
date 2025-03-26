package com.itu.evaluation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itu.evaluation.dto.Payment;
import com.itu.evaluation.service.PaymentService;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/update")
    public String update(RedirectAttributes model,@RequestParam String externalId ,@RequestParam Double amount)throws Exception{
        String message = this.paymentService.updatePaymentAmount(externalId, amount);
        model.addAttribute("message", message);
        return "redirect:/payments/details";
    }

    @GetMapping("/delete")
    public String delete(RedirectAttributes model,@RequestParam String externalId)throws Exception{
        String message = this.paymentService.cancelPayment(externalId);
        model.addAttribute("message", message);
        return "redirect:/payments/details";
    }

    @GetMapping("/details")
    public String getPayments(Model model)throws Exception{
        List<Payment> payments = this.paymentService.getPayments();
        model.addAttribute("payments", payments);
        return "admin/payment/detail";
    }

}
