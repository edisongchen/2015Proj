package com.test.general;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 比较器
 *
 * Created by eds on 2017/5/29.
 */
public class CompatorTest {

    /**
     * list集合中的数据结构map 的排序规则
     * age升序排列，high升序排列，value升序排列
     */
    @Test
    public void compareList(){
        List<Map<String,Object>> list = Lists.newArrayList();
        generateList(list,"admin",1,2f,3l);
        generateList(list,"admin2",1,2f,2l);
        generateList(list,"admin3",1,1f,3l);
        generateList(list,"admin4",1,1f,1l);
        generateList(list,"admin5",0,2f,3l);
        generateList(list,"admin6",0,1f,3l);
        //before sort
        printList(list);
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                if (((Integer)o1.get("age")).compareTo((Integer)o2.get("age")) == 0){
                    if (((Float)o1.get("high")).compareTo((Float)o2.get("high")) == 0) {
                        return ((Long)o1.get("value")).compareTo((Long)o2.get("value"));
                    }else {
                        return ((Float)o1.get("high")).compareTo((Float)o2.get("high"));
                    }
                }else {
                   return ((Integer)o1.get("age")).compareTo((Integer)o2.get("age"));
                }
            }
        });
        //after sort
        printList(list);
    }

    public void generateList(List<Map<String,Object>> list,String name,Integer age,Float high,Long value){
        Map<String,Object> map = Maps.newHashMap();
        map.put("name",name);
        map.put("age",age);
        map.put("high",high);
        map.put("value",value);
        list.add(map);
    }

    private void printList(List<Map<String,Object>> list){
        for(Map<String,Object> map : list){
            System.out.println(map.get("name") +" " + map.get("age") +" "+ map.get("high")+ " " +map.get("value"));
        }
        System.out.println("//////////开始打印/////////////");
    }
}
