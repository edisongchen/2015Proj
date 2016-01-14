package com.proj.common.persistence.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;



/**
 * MyBatis使用该注解标识DAO
 * 如果需要标识某一个Mapper使用这个注解
 * 不然按照默认策略或将所有的Mapper接口做处理
* @author: Administrator
* @version: 1.0, 2015年10月20日
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MyBatisRepository {
	String value() default "";
}
