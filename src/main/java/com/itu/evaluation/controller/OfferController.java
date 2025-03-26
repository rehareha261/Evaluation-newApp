package com.itu.evaluation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itu.evaluation.dto.Offer;
import com.itu.evaluation.service.OfferService;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private OfferService offerService;

    @Autowired
    public OfferController(OfferService service){
        this.offerService = service;
    }


    @GetMapping("/details")
    public String getOffers(Model model) throws Exception{
        Map<String, List<Offer>> offers = this.offerService.getOffers();
        model.addAttribute("offers", offers);
        return "admin/offer/detail";
    }

}
