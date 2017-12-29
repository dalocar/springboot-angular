package com.carrascolimited.springboot.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ExceptionResponse {

    private String errorCode;
    private String errorMessage;
    private List<String> errors = new ArrayList<>();
  
}