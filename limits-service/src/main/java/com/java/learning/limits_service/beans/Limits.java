package com.java.learning.limits_service.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Limits {
    private int minimum;
    private int maximum;
}
