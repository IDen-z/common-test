package com.zmz.testspring;

import org.springframework.stereotype.Component;

@Component
public class TestB {
    private String a;
    private int b;

    public TestB() {
        this.a = "ttt";
        System.err.println("B类的无参构造器执行");
    }

    public TestB(String a, int b) {
        this.a = a;
        this.b = b;
        System.err.println("B类的有参构造器执行");
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
