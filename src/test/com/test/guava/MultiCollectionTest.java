package com.test.guava;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.Collection;

/**
 * 新增的集合类型
 */
public class MultiCollectionTest {

    //rangeMap,rangeSet...

    /**
     * 当我们需要多个索引的数据结构的时候，
     * 通常情况下，我们只能用这种丑陋的Map<FirstName, Map<LastName, Person>>来实现。
     * 为此Guava提供了一个新的集合类型－Table集合类型，来支持这种数据结构的使用场景。
     * Table支持“row”和“column”，而且提供多种视图。
     */
    @Test
    public void tableTest() {

        Table<String, Integer, String> aTable = HashBasedTable.create();

        for (char a = 'A'; a <= 'E'; ++a) {
            for (int b = 1; b <= 5; ++b) {
                aTable.put(Character.toString(a), b, String.format("%s%d", a, b));
            }
        }

        System.out.println(aTable);

        System.out.println(aTable.column(2));
        System.out.println(aTable.column(3));

        System.out.println(aTable.row("D"));
        System.out.println(aTable.row("A"));

        System.out.println(aTable.get("A", 2));

        System.out.println(aTable.contains("D", 3));

        System.out.println(aTable.containsColumn(4));
        System.out.println(aTable.containsRow("C"));

        System.out.println(aTable.columnKeySet());
        System.out.println(aTable.columnMap());
        System.out.println(aTable.rowKeySet());
        System.out.println(aTable.rowMap());

        System.out.println(aTable.remove("A", 3));


        Table<Double, Double, String> table = HashBasedTable.create();
        table.put(23.0336165035d, 113.7616206363d, "广东省东莞市东莞市和兴街22-32号");


        //ClassToInstanceMap
        ClassToInstanceMap<String> instanceMapStr = MutableClassToInstanceMap.create();
        instanceMapStr.put(String.class, "shma");
        System.out.println(instanceMapStr.get(String.class));

        instanceMapStr.put(String.class, "jjq");
        System.out.println(instanceMapStr.getInstance(String.class));
    }


    /**
     * key 和value双向关联的数据结构
     * Key-Value Map Impl     Value-Key Map Impl     Corresponding BiMap
     * HashMap                 	HashMap                 HashBiMap
     * ImmutableMap           　ImmutableMap            ImmutableBiMap
     * EnumMap                　EnumMap                 EnumBiMap
     * EnumMap                  HashMap                 EnumHashBiMap
     */
    @Test
    public void biMapTest() {
        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1, "zhangsan");
        biMap.put(2, "lisi");
        biMap.put(3, "wangwu");
        biMap.put(4, "maliu");

        System.out.println("id>>>name:" + biMap); //{4=maliu, 3=wangwu, 2=lisi, 1=zhangsan}
        System.out.println("name>>>id:" + biMap.inverse());//反转 {maliu=4, wangwu=3, lisi=2, zhangsan=1}

        biMap.inverse().put("zhaoqi", 5);
        System.out.println("id>>>name:" + biMap); //id>>>name:{5=zhaoqi, 4=maliu, 3=wangwu, 2=lisi, 1=zhangsan}
        System.out.println("name>>>id:" + biMap.inverse());//反转name>>>id:{zhaoqi=5, maliu=4, wangwu=3, lisi=2, zhangsan=1}

        //Bimap数据的强制唯一性,key和value必须唯一性。如果k,v不唯一将报错
        //biMap.put(6, "maliu"); //java.lang.IllegalArgumentException
        //System.out.println(biMap);

        //使用forePut 将替换存在的k,v
        biMap.forcePut(6, "maliu");
        System.out.println(biMap); //{5=zhaoqi, 3=wangwu, 2=lisi, 1=zhangsan, 6=maliu}
    }

    /**
     * Multimap:一个key对应多个value
     * Implementation            Keys 的行为类似       　　　		Values的行为类似
     * ArrayListMultimap         HashMap                   ArrayList
     * HashMultimap              HashMap                   HashSet
     * LinkedListMultimap        LinkedHashMap*            LinkedList*
     * LinkedHashMultimap        LinkedHashMap             LinkedHashSet
     * TreeMultimap              TreeMap                   TreeSet
     * ImmutableListMultimap     ImmutableMap              ImmutableList
     * ImmutableSetMultimap      ImmutableMap              ImmutableSet
     */
    @Test
    public void multiMapTest() {
        Multimap<String, StudentScore> multimap = ArrayListMultimap.create();
        multimap.put("zhangsan", new StudentScore(1, 48));
        multimap.put("zhangsan", new StudentScore(2, 83));
        multimap.put("zhangsan", new StudentScore(3, 72));
        multimap.put("lisi", new StudentScore(1, 27));
        multimap.put("lisi", new StudentScore(2, 67));
        multimap.put("lisi", new StudentScore(3, 99));
        multimap.put("wangwu", new StudentScore(1, 80));
        multimap.put("wangwu", new StudentScore(2, 36));
        multimap.put("wangwu", new StudentScore(3, 65));

        System.out.println(multimap.get("zhangsan")); //[StudentScore{1, 48}, StudentScore{2, 83}, StudentScore{3, 72}]
        System.out.println(multimap.size()); //9
        System.out.println(multimap.keys()); //[wangwu x 3, lisi x 3, zhangsan x 3]

        for (String username : multimap.keys()) {
            System.out.println(multimap.get(username));
            System.out.println();
        }

        //修改数据
        Collection<StudentScore> wangwuCollection = multimap.get("wangwu");
        wangwuCollection.clear();
        wangwuCollection.add(new StudentScore(1, 20));
        wangwuCollection.add(new StudentScore(1, 30));
        wangwuCollection.add(new StudentScore(1, 40));

        System.out.println(multimap.get("wangwu"));


        for (StudentScore studentScore : multimap.values()) {
            System.out.println(studentScore);
        }

        multimap.removeAll("zhangsan");
        for (String username : multimap.keys()) {
            System.out.println(username + ":" + multimap.get(username));
        }
    }

    /**
     * 可以在set中多次添加多个相同的元素
     * <p>
     * set：不允许重复，无顺序
     * list：允许重复，有顺序
     * multiset：允许重复，但是不保证顺序
     */
    @Test
    public void multiSetTest() {
        String strWorld = "wer|dffd|ddsa|dfd|dreg|de|dr|ce|ghrt|cf|gt|ser|tg|ghrt|cf|gt|" +
                "ser|tg|gt|kldf|dfg|vcd|fg|gt|ls|lser|dfr|wer|dffd|ddsa|dfd|dreg|de|dr|" +
                "ce|ghrt|cf|gt|ser|tg|gt|kldf|dfg|vcd|fg|gt|ls|lser|dfr";
        String[] words = strWorld.split("\\|");

        Multiset<String> multiset = HashMultiset.create();
        for (String word : words) {
            multiset.add(word);
        }

        for (String word : multiset.elementSet()) {
            System.out.println("word:" + word + ", count:" + multiset.count(word));
        }
        System.out.println("///////////////////");
        if (multiset.contains("lser")) {
            multiset.add("lser", 10);
        }

        multiset.setCount("ce", 19);
        multiset.setCount("cf", 3, 11);//如果cf出现3次则修改为11
        multiset.setCount("dr", 3, 11); //修改不成功

        for (Multiset.Entry<String> entry : multiset.entrySet()) {
            System.out.println("word:" + entry.getElement() + ", count:" + entry.getCount());
        }
    }

    class StudentScore {
        private int courseId;
        private int score;

        public StudentScore(int courseId, int score) {
            this.courseId = courseId;
            this.score = score;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "StudentScore{" +
                    "courseId=" + courseId +
                    ", score=" + score +
                    '}';
        }
    }
}
