package com.example.urlshortener.ErrorHandeling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Responseclass<R> {
    private int errorid;
    private String Error;
    private long timestamp;
}
