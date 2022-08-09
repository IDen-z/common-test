package com.zmz.testspring;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class InterTestA implements InterA {
    @Override
    public void methodA() {
        System.err.println("InterTestA methodA");
    }

    @Override
    public String methodB() {
        System.err.println("InterTestA methodB");
        return "success";
    }
}
