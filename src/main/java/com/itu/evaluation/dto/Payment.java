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
public class Payment {
    private Integer id;
    private String external_id;
    private Double amount;
    private String description;
    private String payment_source;
    private LocalDateTime payment_date;
    private Integer integration_payment_id;
    private String integration_type;
    private Integer invoice_id;
    private LocalDateTime deleted_at;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", externalId='" + external_id + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", paymentSource='" + payment_source + '\'' +
                ", paymentDate=" + payment_date +
                ", integrationPaymentId=" + integration_payment_id +
                ", integrationType='" + integration_type + '\'' +
                ", invoiceId=" + invoice_id +
                ", deletedAt=" + deleted_at +
                ", createdAt=" + created_at +
                ", updatedAt=" + updated_at +
                '}';
    }
}
