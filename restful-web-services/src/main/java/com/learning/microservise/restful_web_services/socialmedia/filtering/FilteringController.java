package com.learning.microservise.restful_web_services.socialmedia.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering(){
        FilteringBean filteringBean = new FilteringBean("value_1", "value_2", "value_3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filteringBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value_2", "value_3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("FilteringBean", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("filtering-list")
    public MappingJacksonValue filteringList(){
        List<FilteringBean> list = Arrays.asList(new FilteringBean("value_1", "value_2", "value_3")
                , new FilteringBean("value_4", "value_5", "value_6")
                , new FilteringBean("value_7", "value_8", "value_9")
                , new FilteringBean("value_10", "value_11", "value_12")
                , new FilteringBean("value_13", "value_14", "value_15"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value_2", "value_3");

        return mappingJacksonValue;
    }
}
