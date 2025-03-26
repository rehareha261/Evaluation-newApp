package com.itu.evaluation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itu.evaluation.Util.CurrencyFormatter;
import com.itu.evaluation.constante.Constante;
import com.itu.evaluation.service.DashboardService;
import com.itu.evaluation.service.InvoiceService;
import com.itu.evaluation.service.OfferService;
import com.itu.evaluation.service.PaymentService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final InvoiceService invoiceService;
    private final OfferService offerService;
    private final PaymentService paymentService;
    private final DashboardService dashboardService;
    private final HttpSession session;

    public AdminController(InvoiceService invoiceService, OfferService offerService, PaymentService paymentService, DashboardService dashboardService, HttpSession session) {
        this.invoiceService = invoiceService;
        this.offerService = offerService;
        this.dashboardService = dashboardService;
        this.paymentService = paymentService;
        this.session = session;
    }

    @GetMapping("/paiement-facture")
    @ResponseBody
    public Map<String, Object> paiementFacture(@RequestParam Integer year)throws Exception{
        Map<String, Object> data = this.dashboardService.getMensuelle(year);
        return data;
    }

    @GetMapping("/logout")
    public String logout(){
        this.session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        try {
            Integer invoiceCount = invoiceService.getCountInvoices();
            Integer offerCount = offerService.getCountOffers();
            Double invoiceTotal = invoiceService.getTotalInvoices();
            Double paymentTotal = paymentService.getTotalPayments();
            Map<String, Object> dataBar = this.dashboardService.getMensuelle(null);
            Map<String, Object> dataPie = this.dashboardService.getRepartitionPayment();
            Map<String, Object> dataLine = this.dashboardService.getEvolution();
            
            String invoiceTotalFormatted = CurrencyFormatter.formatCurrency(invoiceTotal, Constante.localite);
            String paymentTottalFormatted = CurrencyFormatter.formatCurrency(paymentTotal, Constante.localite);

            model.addAttribute("invoiceCount", invoiceCount);
            model.addAttribute("offerCount", offerCount);
            model.addAttribute("invoiceTotal", invoiceTotalFormatted);
            model.addAttribute("paymentTotal", paymentTottalFormatted);
            model.addAttribute("labels", (List<Object>)dataBar.get("labels"));
            model.addAttribute("factureData", (List<Object>)dataBar.get("factureData"));
            model.addAttribute("payeeData", (List<Object>)dataBar.get("payeeData"));
            
            model.addAttribute("pieLabels", (List<Object>)dataPie.get("labels"));
            model.addAttribute("pieData", (List<Object>)dataPie.get("pieData"));

            model.addAttribute("lineLabels", (List<String>)dataLine.get("labels"));
            model.addAttribute("lineData", (List<Object>)dataLine.get("lineData"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("invoiceCount", 0);
            model.addAttribute("offerCount", 0);
            model.addAttribute("invoiceTotal", 0);
            model.addAttribute("paymentTotal", 0);
            model.addAttribute("data", new HashMap<>());
        }

        return "admin/dashboard";
    }
}
