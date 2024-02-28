package com.example.learnspringframework.exercrice;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface DataService {
    int[] retrieveData();
}
//@Component
@Primary
@Repository
class MongoDbDataService implements  DataService{
    @Override
    public int[] retrieveData() {
        return new int[] {11, 22, 33, 44, 55};
    }
}
//@Component
@Repository
class MySQLDataService implements DataService{
    @Override
    public int[] retrieveData() {
        return new int[] {1, 2, 3, 4, 5};
    }
}
