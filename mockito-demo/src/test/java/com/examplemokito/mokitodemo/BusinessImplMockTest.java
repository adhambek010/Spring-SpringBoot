package com.examplemokito.mokitodemo;

import com.examplemokito.mokitodemo.business.BusinessImpl;
import com.examplemokito.mokitodemo.business.DataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusinessImplMockTest {
	@Mock
	private DataService mock;
	@InjectMocks
	BusinessImpl business;
	@Test
	void findTheGreatestFromAllDataBasicTest() {
		when(mock.retrieveAllData()).thenReturn(new int[]{25,15,5,50});
        assertEquals(50, business.findTheGreatestFromAllData());
	}
	@Test
	void findTheGreatestFromAllDataBasicTestOneVale() {
		when(mock.retrieveAllData()).thenReturn(new int[]{35});
		assertEquals(35, business.findTheGreatestFromAllData());
	}
	@Test
	void findTheGreatestFromAllDataBasicTestVale() {
		when(mock.retrieveAllData()).thenReturn(new int[]{});
		assertEquals(Integer.MIN_VALUE, business.findTheGreatestFromAllData());
	}

}

