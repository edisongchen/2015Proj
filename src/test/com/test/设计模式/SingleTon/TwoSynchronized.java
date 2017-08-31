package com.test.设计模式.SingleTon;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by eds on 2017/7/20.
 */
public class TwoSynchronized {
    @Test
    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Product.getInstance().print();
                }
            });
        }
//        for (int i = 0; i < 100; i++) {
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    SingleTon.getInstance().print();
//                }
//            });
//        }
        while (true) ;
    }

}

/**
 * 双重锁判断
 * 若a,b两个线程进入getInstance，当第一个instance为空时，进入锁区间
 * 若a获取了锁，进入同步块，判断instance为空，立即new 对象，之后退出同步块，
 * 此时b线程获得锁，进入同步块，判断instance不为空，就退出。
 * 需要注意的是volitile修饰的instance，被修饰的变量可以确保多个线程能够正确处理。
 * 且需要在jdk1.5之后才能正确使用，由于volatile会屏蔽java虚拟机所做的一些代码优化，可能会导致运行效率
 * 低。因此双重检查锁来实现单例模式也不是一种完美的方式。
 */
class Product {
    private static volatile Product instance;

    private Product() {
        System.out.println("Product Construct init...");
    }

    public static Product getInstance() {
        if (instance == null) {
            synchronized (Product.class) {
                if (instance == null) {
                    instance = new Product();
                }
            }
        }
        return instance;
    }

    public void print() {
        System.out.println("Thead:" + Thread.currentThread().getName() + " execute.");
    }
}

/**
 * 双重锁的改进，Initialization Demand Holder (IoDH)技术
 * 由于静态单例对象没有作为SingleTon的成员变量直接实例化，因此类加载时不会实例化
 * instance.第一次调用getInstance方法时将加载内部类HolderClass，在该类内部定义了一个static变量
 * 此时会首先初始化这个成员变量，由java虚拟机来保证其线程安全性，确保该成员变量只能初始化一次。由于getInstance
 * 没有任何线程锁定，因此其性能不会造成任何影响。
 *
 * 其缺点是与编程语言本身的特性相关，很多面向对象语言不支持IoDH
 */
class SingleTon {

    private SingleTon() {
        System.out.println("///////// SingleTon Constructor init..");
    }

    public static SingleTon getInstance() {
        return HolderClass.instance;
    }

    private static class HolderClass {
        private static final SingleTon instance = new SingleTon();
    }

    public void print() {
        System.out.println("Thead:" + Thread.currentThread().getName() + " execute.");
    }
}
