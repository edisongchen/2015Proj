package com.test.multithread;

import java.awt.geom.RectangularShape;

/**
 * Created by ctg on 2017/2/16.
 */
public class Resource {

    private int number=0;
    private boolean empty=true;

    public synchronized void create(){
        while (!empty){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"生产---"+number);
        empty=false;
        notifyAll();
    }

    public synchronized  void destory(){
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;
        System.out.println(Thread.currentThread().getName()+ " 消费---"+number);
        empty = true;
        notifyAll();
    }
}

class Produce implements  Runnable{
    public Produce(Resource resource) {
        this.resource = resource;
    }

    private Resource resource;

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.create();
        }
    }
}

class Consumer implements  Runnable{

    private Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.destory();
        }
    }
}