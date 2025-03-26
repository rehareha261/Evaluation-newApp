package com.itu.evaluation.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.itu.evaluation.Util.ApiResponse;
import com.itu.evaluation.Util.HeaderGen;
import com.itu.evaluation.constante.ApiUrl;
import com.itu.evaluation.dto.InvoicesTotal;
import com.itu.evaluation.dto.MoisData;

import jakarta.servlet.http.HttpSession;

@Service
public class DashboardService {
    private final RestTemplate restTemplate;
    private final HeaderGen headerGen;
    private final HttpSession session;


    @Autowired
    public DashboardService(RestTemplate restTemplate, HeaderGen headerGen, HttpSession session){
        this.restTemplate = restTemplate;
        this.headerGen = headerGen;
        this.session = session;
    }

    public Map<String, Object> getMensuelle(Integer annee)throws Exception{
        if(annee == null){
            annee = LocalDateTime.now().getYear();
        }
        String url = ApiUrl.DASHBOARD_API_URL_MENSUELLE+"?year="+annee;

        ResponseEntity<ApiResponse<List<Map<String,MoisData>>>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(this.headerGen.generateHeader(this.session)), new ParameterizedTypeReference<ApiResponse<List<Map<String,MoisData>>>>() {});

        List<Map<String, MoisData>> mensuelle = response.getBody().getData().get("mensuelle");
        List<String> labels = new ArrayList<>();
        List<Double> factureData = new ArrayList<>();
        List<Double> payeeData = new ArrayList<>();

        for (Map<String, MoisData> moisData : mensuelle) {
            for (Map.Entry<String, MoisData> entry : moisData.entrySet()) {
                String mois = entry.getKey();
                MoisData data = entry.getValue();
                
                labels.add(mois);
                factureData.add(data.getFacture());
                payeeData.add(data.getPayee());
            }
        }

        Map<String, Object> chartData = new HashMap<>();

        chartData.put("labels", labels);
        chartData.put("factureData", factureData);
        chartData.put("payeeData", payeeData);

        return chartData;
    }


    public Map<String, Object> getRepartitionPayment()throws Exception{
        String url = ApiUrl.DASHBOARD_API_URL_REPARTITION;

        ResponseEntity<ApiResponse<List<Map<String, String>>>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(this.headerGen.generateHeader(this.session)), new ParameterizedTypeReference<ApiResponse<List<Map<String,String>>>>() {});

        List<Map<String, String>> mensuelle = response.getBody().getData().get("repartition");
        List<String> labels = new ArrayList<>();
        List<Double> factureData = new ArrayList<>();

        for (Map<String, String> moisData : mensuelle) {
            labels.add(moisData.get("payment_source"));
            factureData.add(Double.parseDouble(moisData.get("somme")));
        }

        Map<String, Object> chartData = new HashMap<>();

        chartData.put("labels", labels);
        chartData.put("pieData", factureData);

        return chartData;
    }

    
    public Map<String, Object> getEvolution()throws Exception{
        String url = ApiUrl.DASHBOARD_API_URL_EVOLUTION;
        Map<String, Object> chartData = new HashMap<>();

        chartData.put("labels", new ArrayList<>());
        chartData.put("lineData", new ArrayList<>());    
        try{
            ResponseEntity<ApiResponse<List<Object>>> draft = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(this.headerGen.generateHeader(this.session)), new ParameterizedTypeReference<ApiResponse<List<Object>>>() {});
            if(!draft.getBody().getData().get("evolution").isEmpty()){
                ResponseEntity<ApiResponse<Map<String, String>>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(this.headerGen.generateHeader(this.session)), new ParameterizedTypeReference<ApiResponse<Map<String,String>>>() {});
    
                Map<String, String> mensuelle = response.getBody().getData().get("evolution");
                List<String> labels = new ArrayList<>();
                List<Double> factureData = new ArrayList<>();
        
                
                for (Map.Entry<String, String> entry : mensuelle.entrySet()) {
                    String annee = entry.getKey();
                    labels.add(annee);
        
                    factureData.add(Double.parseDouble(entry.getValue()));
                }
        
        
                chartData.put("labels", labels);
                chartData.put("lineData", factureData);    
            }
        }catch(Exception e){
            ResponseEntity<ApiResponse<Map<String, String>>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(this.headerGen.generateHeader(this.session)), new ParameterizedTypeReference<ApiResponse<Map<String,String>>>() {});
    
            Map<String, String> mensuelle = response.getBody().getData().get("evolution");
            List<String> labels = new ArrayList<>();
            List<Double> factureData = new ArrayList<>();
    
            
            for (Map.Entry<String, String> entry : mensuelle.entrySet()) {
                String annee = entry.getKey();
                labels.add(annee);
    
                factureData.add(Double.parseDouble(entry.getValue()));
            }
    
    
            chartData.put("labels", labels);
            chartData.put("lineData", factureData);
        }
        return chartData;
    }

}
