package com.itu.evaluation.Util;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse <T>{
    private Integer status;
    private Map<String, T> data;
}
