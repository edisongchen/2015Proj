/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.proj.common.config;

import java.util.Map;

import com.google.common.collect.Maps;
import com.proj.common.util.PropertiesLoader;

/**
 * 全局配置类
 * 
 * @author ThinkGem
 * @version 2013-03-23
 */
public class Global {

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader propertiesLoader = new PropertiesLoader("application.properties");

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = propertiesLoader.getProperty(key).trim();
            map.put(key, value);
        }
        return value;
    }

    // ///////////////////////////////////////////////////////

    /**
     * 获取管理端根路径
     */
    /*
     * public static String getAdminPath() {
     * String dir = getConfig("adminPath");
     * Assert.hasText(dir, "配置文件里没有配置adminPath属性");
     * 
     * return dir;
     * }
     */

    /**
     * 获取前端根路径
     */
    /*
     * public static String getFrontPath() {
     * String dir = getConfig("frontPath");
     * Assert.hasText(dir, "配置文件里没有配置frontPath属性");
     * 
     * return dir;
     * }
     */

    /**
     * 获取URL后缀
     */
    /*
     * public static String getUrlSuffix() {
     * String dir = getConfig("urlSuffix");
     * Assert.hasText(dir, "配置文件里没有配置urlSuffix属性");
     * 
     * return dir;
     * }
     */

}
