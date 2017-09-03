package com.test.java8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 几种获得stream的方式
 * ref https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/
 */
public class StreamTest {
    //获得流的几个常用方法
    // 1. Individual values
//    Stream stream = Stream.of("a", "b", "c");
//    // 2. Arrays
//    String [] strArray = new String[] {"a", "b", "c"};
//    stream = Stream.of(strArray);
//    stream = Arrays.stream(strArray);
//    // 3. Collections
//    List<String> list = Arrays.asList(strArray);
//    stream = list.stream();

    /**
     * stream 分为 Intermediate :相当于是一系列操作组成的集合，流不会遍历
     * Terminal 一个流一个Terminal操作，也是最后一个操作，执行后才会真正遍历。并生成一个结果或side effect 当流被操作后，就不能二次操作。
     * <p>
     * short-circuiting 对一个Intermediate操作，它接受一个无限大的stream，但返回一个有限的新Stream
     * 或对于一个Terminal操作，它接受一个无限大的Stream.但能在有限时间计算结果.
     */

    @Test
    public void getStream() {
        //java8提供了 IntStream,DoubleStream,LongStream。相比Stream<Integer,Long,Double> 性能更好
        IntStream.of(new int[]{1, 2, 3, 4, 5, 6})//将一个数组转为流
                .filter(i -> i % 2 == 0)//过滤奇数 Intermediate
                .forEach(System.out::println); //Terminal foreach调用PrintStream.println方法
        //简单说，使用Stream就是实现一个filter-map-reduce 过程(map 映射)，产生一个最终结果，或导致一个副作用(side effect)
    }

    @Test
    public void streamConvert() {
        //流的转换
        String[] strArray1 = Stream.of("a", "11", "aaaa", "11", "cc", "a").toArray(String[]::new);
        System.out.println(strArray1);
        List<String> list1 = Stream.of("a", "11", "aaaa", "11", "cc", "a").collect(Collectors.toList());
        System.out.println(list1);
        //
        Set<String> set1 = Stream.of("a", "11", "aaaa", "11", "cc", "a").collect(Collectors.toSet());
        System.out.println(set1);
        Stack stack1 = Stream.of("a", "11", "aaaa", "11", "cc", "a").collect(Collectors.toCollection(Stack::new));
        System.out.println(stack1);

        String str = Stream.of("a", "11", "aaaa", "11", "cc", "a").collect(Collectors.joining()).toString();
        System.out.println(str);
    }


    /**
     * Intermediate：
     * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
     * <p>
     * Terminal：
     * forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、
     * findAny、 iterator
     * <p>
     * Short-circuiting：
     * anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
     */

    @Test
    public void mapTest() {
        //map 将input stream的每个元素，1:1映射成 output stream的另外一个元素
        //转化为大写
        List<String> output = Stream.of("admin", "this", "sss")
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(output);

    }

    @Test
    public void flatMapTest() {
        //flatMap 把input stream中的层级结构扁平化，将最底层的元素抽出来放到一起，
        //最终output的新Stream里面已经没有List了，都是直接数字
        Stream.of(Arrays.asList(1), Arrays.asList(222, 33, 4), Arrays.asList(6, 7, 221))
                .flatMap(list -> list.stream())
//                .forEach(i-> System.out.println(i)) //以下的forEach等价
                .forEach(System.out::println);
        List<Map<String, Object>> listMap = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("name", "123" + i);
            map.put("age", 18 + i);
            listMap.add(map);
        }

        Stream.of(listMap)
                .forEach(System.out::println);
    }

    @Test
    public void peekTest() {
        //peek是intermediate的
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    @Test
    public void reduceTest() {
        //将Stream元素组合起来，它提供了一个起始值(种子),然后依照运算规则(BinaryOperator)和前面的Stream
        //的第一个,第二个，第n个元素组合。可以认为，字符串拼接，sum,min,max,average都是特殊的reduce
        Integer sum = IntStream.of(1, 2, 3, 4, 5)
                .reduce(0, (a, b) -> a + b);//reduce(0,Integer::sum)
        System.out.println(sum);
        Integer sum2 = IntStream.of(1, 2, 3, 4, 5)
                .reduce(Integer::sum)//reduce没有起始值，返回Optional
                .getAsInt();

    }

    @Test
    public void groupingBy() {
        List<User> listMap = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            User user = new User();
            user.setId(random.nextInt(100));
            user.setAge(random.nextInt(4));
            listMap.add(user);
        }
        System.out.println(listMap);

        Map<Integer, Long> groupMap =
                listMap.stream().limit(10)
                        .collect(Collectors.groupingBy(User::getAge, Collectors.counting()));

        Map<Integer, List<User>> groupMap2 = listMap.stream()
                .collect(Collectors.groupingBy(User::getAge));

        System.out.println(groupMap);
        System.out.println("/////////////");
        System.out.println(groupMap2);
    }

}

class User {
    private Integer id;
    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}