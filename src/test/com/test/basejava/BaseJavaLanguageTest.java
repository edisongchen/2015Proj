package com.test.basejava;

import org.junit.Test;

/**
 * 
 * 父类静态块>子类静态块
 * <p/>
 * 父类块>子类块(并且每次new都会执行，不管顺序，都优于构造方法)
 * <p/>
 * 父类构造函数 > 子类构造函数
 * <p/>
 */
public class BaseJavaLanguageTest {

    @Test
    public void ttt() {
        int result = 0;
        int i = 2;
        switch (i) {
            case 1:
                result = result + i;
            case 2:
                result = result + i * 2;
            case 3:
                result = result + i * 3;
        }
        System.out.println(result);

        new Child("abd");
    }

    @Test
    public void test1() {
        String str = "123";
        char[] ch = {'a', 'b', 'c'};
        new BaseJavaLanguageTest().exchange(str, ch);
        System.out.println(str);
        System.out.println(ch);
    }

    public void exchange(String str, char[] ch) {
        str = "testOK";
        ch[0] = 'g';
    }
}


class People {
    String name;
    {
        System.out.println("Instance init A People");
    }

    public People() {
        System.out.println(1);
    }

    {
        System.out.println("Instance init B People");
    }

    public People(String name) {
        System.out.println(2);
        this.name = name;
    }
}


class Child extends People {
    People father;
    {
        System.out.println("Instance init A Child");
    }

    public Child(String name) {
        System.out.println(3);
        this.name = name;
        father = new People(name + ":F");
    }

    {
        System.out.println("Instance init B Child");
    }

    public Child() {
        System.out.println(4);
    }
}

