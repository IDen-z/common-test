package com.zmz.testspring;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
public class InterTestB implements InterA {
    @Override
    public void methodA() {
        System.err.println("InterTestB methodB");
    }

    @Override
    public String methodB() {
        System.err.println("InterTestB methodB");
        return "success";
    }
}
