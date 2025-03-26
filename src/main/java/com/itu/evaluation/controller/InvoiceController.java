package com.itu.evaluation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import com.itu.evaluation.Util.ObjectMapperExtension;
import com.itu.evaluation.dto.Invoices;
import com.itu.evaluation.dto.InvoicesTotal;
import com.itu.evaluation.service.InvoiceService;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {
    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService service){
        this.invoiceService = service;
    }


    @GetMapping("/details")
    public String getInvoices(Model model) throws Exception{
        Map<String, List<Invoices>> offers = this.invoiceService.getInvoices();
        model.addAttribute("invoices", offers);
        return "admin/invoice/detail";
    }

    @GetMapping("/details/total")
    public String getInvoicesTotal(Model model)throws Exception{
        List<InvoicesTotal> invoicesTotals = this.invoiceService.getTotalInvoicesDetail();
        model.addAttribute("invoices", invoicesTotals);
        return "admin/invoice/detail-total";
    }

    @GetMapping("/remise")
    public String getRemiseForm()throws Exception{
        return "admin/conf/remise";
    }


    @PostMapping("/remise")
    public String setRemise(@RequestParam Double remise, Model model)throws Exception{
        try{
            String message = this.invoiceService.setRemise(remise);
            model.addAttribute("message", message);
        }
        catch(Exception e){
            HttpClientErrorException exception = (HttpClientErrorException)(e);
            String message = exception.getResponseBodyAsString();
            message = ObjectMapperExtension.extractErrorMessage(message);
            model.addAttribute("message", message);
            System.out.println(message);
            model.addAttribute("message", message);
        }
        return "admin/conf/remise";
    }

}
