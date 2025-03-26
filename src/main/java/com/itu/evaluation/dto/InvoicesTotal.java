package com.itu.evaluation.dto;

import com.itu.evaluation.Util.CurrencyFormatter;
import com.itu.evaluation.constante.Constante;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoicesTotal {
    private Invoices invoice;
    private Double total;    
    private String formate;

    public String getFormate(){
        String s = CurrencyFormatter.formatCurrency(this.getTotal(), Constante.localite);
        this.formate = s;
        return this.formate;
    }
}
