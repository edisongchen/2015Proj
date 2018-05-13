package com.test.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * Strings字符串处理
 * isNullOrEmpty  判断非空
 * commonPrefix/commonSuffix 输出共同的前缀/后缀
 * padStart/padEnd 字符补全 向前/向后
 * Splitter  字符串拆分
 * Joiner 字符串合并
 * repeat  字符串重复拼接
 * ref :https://my.oschina.net/shma1664/blog/596904
 */
public class StringsTest {

    @Test
    public void isNullOrEmptyTest() {
        System.out.println(Strings.isNullOrEmpty(null));//true
        System.out.println(Strings.isNullOrEmpty("")); //true
        System.out.println(Strings.isNullOrEmpty(" ")); //false
        System.out.println(Strings.isNullOrEmpty(" xxx")); //false
    }

    /**
     * 截取两个字符串的相同前/后 缀
     */
    @Test
    public void commonSuffixPrefixTest() {
        System.out.println(Strings.commonPrefix("admin doge", "adsage doge"));//ad
        System.out.println(Strings.commonSuffix("admie doge", "adsage doge"));// doge
    }

    /**
     * 字符串 补全
     */
    @Test
    public void padTest() {
        int length = 8;
        System.out.println(Strings.padEnd("123", length, '0'));//12300000
        System.out.println(Strings.padStart("123", length, '0'));//00000123
    }

    /**
     * 字符传拆分
     */
    @Test
    public void splitterTest() {
        Iterable<String> iterable = Splitter.onPattern("[,，.;]")
                .trimResults()
                .omitEmptyStrings()
                .split("名称1,张三，lishi,,空格 注意，分号; 前后空格 ");
        for (String s : iterable) {
            System.out.println(s);
        }

        //将拆分后的字符串再次切分
        Map<String, String> kvs = Splitter.onPattern("[;,]")
                .trimResults()
                .omitEmptyStrings()
                .withKeyValueSeparator("=")
                .split("a=1; b=2, c=3");

        System.out.println(kvs); //{a=1, b=2, c=3}
    }

    /**
     * 字符串合并
     */
    @Test
    public void joinTest() {
        //zs-lishi-wangwu
        System.out.println(Joiner.on("-").join(new String[]{"zs", "lishi", "wangwu"}));
        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("k1", "v1");
        dataMap.put("k2", "v2");
        dataMap.put("k3", "v3");
        dataMap.put("k4", "v4");
        //k1&v1,k2&v2,k3&v3,k4&v4
        System.out.println(Joiner.on(",").withKeyValueSeparator("&").join(dataMap));
    }

    /**
     * 字符串重复
     */
    @Test
    public void repeatTest() {
        //112233112233
        System.out.println(Strings.repeat("112233", 2));
    }


}
