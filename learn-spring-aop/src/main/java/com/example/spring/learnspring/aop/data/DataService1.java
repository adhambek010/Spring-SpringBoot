package com.example.spring.learnspring.aop.data;

import com.example.spring.learnspring.aop.annotations.TrackTime;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class DataService1 {
    @TrackTime
    public int[] retrieveData() {
        return new int[] {11, 22, 33, 44, 55};
    }
}
