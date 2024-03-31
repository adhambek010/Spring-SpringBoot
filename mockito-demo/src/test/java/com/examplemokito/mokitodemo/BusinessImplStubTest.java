package com.examplemokito.mokitodemo;

import com.examplemokito.mokitodemo.business.BusinessImpl;
import com.examplemokito.mokitodemo.business.DataService;

import static org.junit.jupiter.api.Assertions.assertEquals; 

import org.junit.jupiter.api.Test;

class BusinessImplStubTest {

	@Test
	void findTheGreatestFromAllDataBasicTest() {
		DataService stub = new DataServiceStub();

		BusinessImpl business = new BusinessImpl(stub);
		int result = business.findTheGreatestFromAllData();
		assertEquals(50, result);
	}

}

class DataServiceStub implements DataService {

	@Override
	public int[] retrieveAllData() {
		return new int[]{25,15,5,50};
	}
}
