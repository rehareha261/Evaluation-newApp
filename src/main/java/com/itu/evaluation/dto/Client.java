package com.itu.evaluation.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private Integer id;
    private String external_id;
    private String address;
    private String zipcode;
    private String city;
    private String company_name;
    private String vat;
    private String company_type;
    private Long client_number;
    private Integer user_id;
    private Integer industry_id;
    private LocalDateTime deleted_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", externalId='" + external_id + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + company_name + '\'' +
                ", vat='" + vat + '\'' +
                ", companyType='" + company_type + '\'' +
                ", clientNumber=" + client_number +
                ", userId=" + user_id +
                ", industryId=" + industry_id +
                ", deletedAt=" + deleted_at +
                ", createdAt=" + created_at +
                ", updatedAt=" + updated_at +
                '}';
    }
}
