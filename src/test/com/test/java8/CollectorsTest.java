package com.test.java8;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

/**
 * Collectors收集器
 * toList
 * toMap
 * groupingMap
 */
public class CollectorsTest {

    /**
     * 输入类型是T，输出类型是List<T>
     */
    @Test
    public void toList(){
        List<String> list = Stream.of("1","2","3").collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * 简单的版本，接受一对函数作为输入，
     * 一个生成key,另一个生成value
     */
    @Test
    public void toMapSimple(){
        Map<Integer,Integer> map = Stream.of(new Human(1,"name1",11),new Human(3,"name3",13),new Human(2,"name2",12))
                .collect(Collectors.toMap(obj->obj.getId(),obj->obj.getAge()));
        System.out.println(map);
    }

    @Test
    public void toMapComplex(){
        //TODO 复杂toMap 收集器可以指定map类型和解决键冲突
    }
}

class Human{
    private Integer id;
    private String name;
    private Integer age;

    public Human(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
