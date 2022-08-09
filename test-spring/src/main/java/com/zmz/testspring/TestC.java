package com.zmz.testspring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestC implements ApplicationContextAware {

    TestC() {
        System.err.println("C的无参构造器执行");
    }

    @Autowired
    private TestA testA;

    @Autowired
    private TestB testB;

    @Autowired
    private List<InterA> listA;

    @RequestMapping("/test")
    public String test() {
        return "success";
    }

    @RequestMapping("/test2")
    public String test2() {
        InterA interA = listA.get(0);
        InterA interB = listA.get(1);
        interA.methodA();
        interB.methodA();
        String[] beanNamesForType = applicationContext.getBeanNamesForType(InterA.class);
        return "success";

    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
