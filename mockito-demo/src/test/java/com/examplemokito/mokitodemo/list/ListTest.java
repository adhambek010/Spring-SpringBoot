package com.examplemokito.mokitodemo.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListTest {
    @Mock
    private List listMock;
    
    @Test 
    void simpleTest(){
        when(listMock.size()).thenReturn(3);
        assertEquals(3, listMock.size());
    }

    @Test
    void multipleReturns() {
        when(listMock.size()).thenReturn(1).thenReturn(3);
        assertEquals(1, listMock.size());
        assertEquals(3, listMock.size());
    }

    @Test
    void specificParameters() {
        when(listMock.get(0)).thenReturn("String");
        assertEquals("String", listMock.get(0));
    }

    @Test
    void genericParameters() {
        when(listMock.get(anyInt())).thenReturn("String");
        assertEquals("String", listMock.get(0));
    }
}

