package com.itu.evaluation.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    private int id;
    private String external_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sent_at;
    private String source_type;
    private int source_id;
    private int client_id;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    private LocalDateTime created_at;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    private LocalDateTime updated_at;
    private Client client;

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", externalId='" + external_id + '\'' +
                ", sentAt=" + sent_at +
                ", sourceType='" + source_type + '\'' +
                ", sourceId=" + source_id +
                ", clientId=" + client_id +
                ", status='" + status + '\'' +
                ", createdAt=" + created_at +
                ", updatedAt=" + updated_at +
                ", client=" + (client != null ? client.getCompany_name() : "No client") +
                '}';
    }
}
