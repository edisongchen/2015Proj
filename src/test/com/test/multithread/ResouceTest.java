package com.test.multithread;

import org.junit.Test;

/**
 * Created by ctg on 2017/2/16.
 */
public class ResouceTest {
    @Test
    public void testResouce(){
        Resource resource = new Resource();
        Thread consumer1 =new Thread(new Consumer(resource));
//        Thread consumer2 =new Thread(new Consumer(resource));
//        Thread consumer3 =new Thread(new Consumer(resource));
        Thread producer1 =new Thread(new Produce(resource));
        Thread producer2 =new Thread(new Produce(resource));

        consumer1.start();
//        consumer2.start();
//        consumer3.start();
        producer1.start();
        producer2.start();
        while (true);

    }
}
