package com.examplemokito.mokitodemo.business;

import org.springframework.beans.factory.annotation.Autowired;

public class BusinessImpl {

    private final DataService service;

    public BusinessImpl(DataService service) {
        this.service = service;
    }

    public int findTheGreatestFromAllData() {
        int[] data = service.retrieveAllData();

        int greatestValue = Integer.MIN_VALUE;
        for(int value : data){
            if(value > greatestValue){
                greatestValue = value;
            }
        }
        return greatestValue;
    }

}

