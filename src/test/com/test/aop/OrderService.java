package com.test.aop;

import org.junit.Test;

/**
 * Created by eds on 2018/3/17.
 */
public class OrderService {

    public void checkInventory(String id) {
        System.out.println("checkInventory method run。。。" + id);
    }

    @Test
    public void test() {
        new OrderService().checkInventory("xxx");
    }
}
