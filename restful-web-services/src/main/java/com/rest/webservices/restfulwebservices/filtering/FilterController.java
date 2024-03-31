package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilterController {
    @GetMapping("/filtering")
    private MappingJacksonValue filtering() {
        SomeBean bean = new SomeBean("value 1", "value 2", "value 3");

        MappingJacksonValue jacksonValue = new MappingJacksonValue(bean);

        return setCustomFilters(jacksonValue);
    }

    @GetMapping("/filtering-list")
    private MappingJacksonValue filteringAsList() {
        List<SomeBean> list =  Arrays.asList(new SomeBean("value 1", "value 2", "value 3"),
                new SomeBean("value 4", "value 5", "value 6"),
                new SomeBean("value 7", "value 8", "value 9")
        );
        MappingJacksonValue jacksonValue = new MappingJacksonValue(list);


        return setCustomFilters(jacksonValue);
    }
    private MappingJacksonValue setCustomFilters(MappingJacksonValue value){
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        value.setFilters(filters);
        return value;
    }

}

@AllArgsConstructor
@Getter
@JsonFilter("SomeBeanFilter")
//@JsonIgnoreProperties("field1")
class SomeBean {
    private String field1;
    //@JsonIgnore
    private String field2;
    private String field3;
}