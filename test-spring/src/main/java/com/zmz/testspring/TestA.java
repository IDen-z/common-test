package com.zmz.testspring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class TestA implements ApplicationContextAware ,Comparable<String>{
    private String a;
    private int b;

    public TestA() {
        System.err.println("A类的无参构造器执行");
    }

    @Autowired
    public TestA(TestB testB) {
        String a = testB.getA();
        System.err.println(a);
        System.err.println("A类的有参构造器执行");
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String displayName = applicationContext.getDisplayName();
        System.err.println(displayName);
        System.err.println("setApplication 方法执行");
    }

    @Override
    public int compareTo(String o) {

        return 0;
    }
}
