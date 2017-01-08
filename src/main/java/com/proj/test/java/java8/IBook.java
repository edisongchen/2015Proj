package com.proj.test.java.java8;

/**
 * jdk8 支持 接口中定义普通方法 使用default和static关键字
 * 区别：default 定义的普通方法用对象调用
 * static 定义的方法用接口名调用
 * Created by ctg on 2017/1/8.
 */
public interface IBook {

    public void read();
    default void write(){
        System.out.println("fff");
    };

    static void study(){
        System.out.println("我要不断学习!");
    }

}

class Book implements IBook{

    @Override
    public void read() {

    }
}

class IBookTest{
    public static void main(String args[]){
        IBook book = new Book();
        book.write();
        IBook.study();
    }
}
