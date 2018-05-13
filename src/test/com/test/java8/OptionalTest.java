package com.test.java8;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * Java痛点 调用一个方法得到返回值，却不能直接将返回值 作为参数去调用别的方法，
 * 而是先要判断这个返回值是否为null Guava 提供Optional解决(java8 也提供了)
 * https://blog.csdn.net/L_Sail/article/details/78868673
 * <p>
 * of ofNullable isPresent get ifPresent orElse map
 * </p>
 */
public class OptionalTest {

    /**
     * of 为 非空的值 创建Optional
     */
    @Test
    public void of() {
        Optional<String> action = Optional.of("xxx");
        // 异常
        Optional<String> error = Optional.of(null);
    }

    /**
     * 如果指定值为null 则返回一个空的Optional
     */
    @Test
    public void ofNullable() {
        Optional empty = Optional.ofNullable(null);
        System.out.println(empty.equals(Optional.empty()));
    }


    /**
     * 如果存在返回true 否则返回false
     */
    @Test
    public void isPresent() {
        // Optional name = Optional.of("Alice");
        Optional name = Optional.empty();
        if (name.isPresent()) {
            System.out.println("name:" + name.get());
        } else {
            System.out.println("name not exists");
        }
    }

    /**
     * Optional实例有值 则调用consumer 否则不做处理 这个是比较常用的
     */
    @Test
    public void ifPresent() {
        Optional name = Optional.of("Alice");
        // Optional name = Optional.empty();
        name.ifPresent(s -> {
            s = s + " consumer";
            System.out.println("name:" + s);
        });
    }

    @Test
    public void get() {
        Optional name = Optional.of("Alice");
        // exception:No value present
        // Optional name = Optional.empty();
        System.out.println(name.get());
    }

    /**
     * 如果有值 则将其返回 否则返回指定的其它值
     */
    @Test
    public void orElse() throws Throwable {
        // Optional name = Optional.of("Alice");
        Optional name = Optional.empty();
        System.out.println(name.orElse("defaultName"));
        // orElseGet支持 lambada表达式
        System.out.println(name.orElseGet(() -> new Random().nextInt(10) + "defaultName"));
        // orElseThrow 如果有值则返回，否则抛出 supplier接口创建的异常
        // name.orElseThrow(ArrayIndexOutOfBoundsException::new);
        List<String> list = new ArrayList<>();
        list = null;
        List<String> list2 = Optional.ofNullable(list).orElse(Collections.emptyList());
        System.out.println("list2:" + list2);
    }

    /**
     * 如果有值 则对其执行调用mapping函数得到返回值。 如果返回值不为null,
     * <p>
     * 则创建包含mapping返回值的Optional作为map方法 返回值，否则 返回空Optional
     * </p>
     */
    @Test
    public void map() {
        Optional<String> name = Optional.ofNullable("sss");
        Optional<String> upperName = name.map((value) -> value.toUpperCase());
        System.out.println(upperName.orElse("No value found"));
        StringBuffer s = new StringBuffer();
        name.map(k -> (s.append(k)));
        System.out.println("s:" + s.toString());
        Map<String, String> map = Maps.newHashMap();
        map.put("123", "1234abc");
        map.put("223", "2234");
        Optional<Map<String, String>> optionalMap = Optional.of(map);
        String value = optionalMap.map(m -> m.get("123")).orElse("");
        System.out.println("value:" + value);
        // 避免了过去的如下写法
        // if (optionalMap.isPresent()){
        // value = optionalMap.get().get("123");
        // } else {
        // value="";
        // }
        // map 可以继续map
        value = optionalMap.map(k -> k.get("123")).map(v -> v.toUpperCase()).orElse("");
        System.out.println("value:" + value);

    }

    /**
     * 如果有值，执行mapping函数返回Optional类型返回值.否则返回空Optional flatMap与Map方法类似，区别在与flatMap中的mapper返回值必须是Optional的
     * 调用结束时，flatMap不会对结果用Optional封装
     */
    @Test
    public void flatMap() {
        Map<String, String> map = Maps.newHashMap();
        map.put("123", "1234abc");
        map.put("223", "2234");
        Optional<Map<String, String>> optionalMap = Optional.of(map);
        Optional optional = optionalMap.flatMap(v -> Optional.of(v.get("123").toUpperCase()));
        System.out.println("" + optional.orElse("NULL"));
    }


    ///////////////////////////最佳实践//////////////////////////
    /**
     * Optional中 应该减少isPresent() 和 get()的使用
     * 使用如下
     * map
     * orElse
     * orElseGet
     * orElseThrow
     * ifPresent
     * filter
     * flatMap
     *
     * 而get和isPresent更应该私有化
     */


}
