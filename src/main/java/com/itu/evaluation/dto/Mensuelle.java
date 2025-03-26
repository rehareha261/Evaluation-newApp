package com.itu.evaluation.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mensuelle {
    private Map<String, MoisData> data;
}
