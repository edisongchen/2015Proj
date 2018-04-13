package com.test.aop.weave;

import org.aspectj.lang.annotation.Before;

/**
 * Created by eds on 2018/3/17.
 */
public class LogPointCut {

    @Before(value = "execution(* com.test.aop.OrderService.checkInventory(..))")
    public void bebefore(){
        System.out.println("before log...");
    }
}
