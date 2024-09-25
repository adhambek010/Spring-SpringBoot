package com.learning.microservise.restful_web_services.socialmedia.filtering;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonFilter("FilteringBean")
public class FilteringBean {
    private String value_1;
//    @JsonIgnore
    private String value_2;
    private String value_3;
}
