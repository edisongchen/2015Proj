package com.test.thread;

import org.junit.Test;

/**
 * Created by eds on 2017/5/30.
 */
public class MultiThread {
    @Test
    public void testRun(){
        String a ="test s";
        switch (a){
            case "a":
                System.out.println("1");
            case "test s":
                System.out.println("2");
            case "3":
                System.out.println("3");
        }

    }

    class Thread1 implements  Runnable{

        @Override
        public void run() {

        }
    }

    class Thread2 implements  Runnable{

        @Override
        public void run() {

        }
    }
}
