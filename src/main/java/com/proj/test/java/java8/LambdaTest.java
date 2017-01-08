package com.proj.test.java.java8;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * (params)->expression
 * (params)->statement
 * (params)->{statement}
 * Created by ctg on 2017/1/8.
 */
public class LambdaTest {

    /**
     * lambda本质是一个匿名方法
     */
    @Test
    public void addTest(){
        new Thread(()-> System.out.println("In Java8，Lambda expression rocks!")).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("旧版匿名类使用");
            }
        }).start();
    }

    @Test
    public void CollectionTest(){
        List list = Arrays.asList("admin1","admin2","admin3","admin4","admin5");
        list.forEach(e->
        {
            if (!e.equals("admin1")){
                System.out.println(e);
            }
        });

        System.out.println("///////////");
        //方法引用由::操作符表示
        //看起来像c++的作用域解析运算符
        list.forEach(System.out::println);
    }

    /**
     * java8 添加了java.util.function 包含了汗多类来支持lambda
     * 其中一个是Predicate java.util.function.Predicate
     * 该接口非常适合做过滤
     */
    @Test
    public void Predicate1(){
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Languages which starts with J :");
        filter(languages, (str)->str.toString().startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->str.toString().endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str)->str.toString().length() > 4);
    };

    public void  filter(List names, Predicate predicate){
        /*for(Object name : names){
            if (predicate.test(name)) {
                System.out.println(name+" ");
            }
        }*/
        names.stream().filter(name->(predicate.test(name))).forEach((name)->{
            System.out.println(name +" ");
        });
    }


    /**
     * Predicate 中允许两个或更多的Predicate合成一个。提供了类似的AND,OR的方法
     * 用于将传入filter()方法的条件
     */
    @Test
    public void usePredicate(){
        List names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        Predicate<String> startWithJ = (n)->n.startsWith("J");
        Predicate<String> fourLetterLong = (n)->n.length()==4;

        names.stream().filter(startWithJ.and(fourLetterLong))
                .forEach((n)-> System.out.println("name start with 'J' and lenght 4:"+n));
    }

    @Test
    public void MapReduce(){
        
    }

}
