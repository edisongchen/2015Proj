package com.test.com.test.java;

/**
 * Created by ctg on 2017/2/18.
 */
public class Extends {
    public Extends(){
        System.out.println("2222");
    }
    {
        System.out.println("111");
    }
    static {
        System.out.println("parent static...");
    }
}
class Extend2 extends  Extends{
    public Extend2(){
        System.out.println("333");
    }
    {
        System.out.println("444");
    }
    static{
        System.out.println("child static ...");
    }

    public static void main(String args[]){
        System.out.println("///////start");
        new Extend2();
        System.out.println(".....");
        new Extend2();
        System.out.println("///////end");
    }
}

