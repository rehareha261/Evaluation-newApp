package com.itu.evaluation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoisData {
    private Double facture;
    private Double payee;

    @Override
    public String toString(){
        return "facture "+this.getFacture()+" payee "+this.getPayee();
    }
}
