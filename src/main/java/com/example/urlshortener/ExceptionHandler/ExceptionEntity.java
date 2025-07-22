package com.example.urlshortener.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionEntity {
    private int errorcode;
    private String errorMessage;
    private long timestamp;
}
