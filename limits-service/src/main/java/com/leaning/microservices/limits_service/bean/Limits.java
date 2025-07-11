package com.leaning.microservices.limits_service.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Limits {
    private int maximum;
    private int minimum;
}
