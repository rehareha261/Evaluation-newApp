package com.itu.evaluation.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoices {
    private Integer id;
    private String external_id;
    private String status;
    private Integer invoice_number;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sent_at;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    private LocalDateTime due_at;
    private Integer integration_invoice_id;
    private String integration_type;
    private String source_type;
    private Integer source_id;
    private Integer client_id;
    private Integer offer_id;
    private LocalDateTime deleted_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Client client;


    @Override
    public String toString() {
        return "InvoiceDTO{" +
                "id=" + id +
                ", external_id='" + external_id + '\'' +
                ", status='" + status + '\'' +
                ", invoice_number=" + invoice_number +
                ", sent_at=" + sent_at +
                ", due_at=" + due_at +
                ", integration_invoice_id=" + integration_invoice_id +
                ", integration_type='" + integration_type + '\'' +
                ", source_type='" + source_type + '\'' +
                ", source_id=" + source_id +
                ", client_id=" + client_id +
                ", offer_id=" + offer_id +
                ", deleted_at=" + deleted_at +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
