package com.test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * 反射的几种使用 通过getClass()方法 通过Class.forName()方法； 使用类.class 通过类加载器实现，getClassLoader()
 */
public class CreateReflect {
    /**
     * TODO getFields时 我如何知道field是父类的还是子类的 getFields()：获得类及父类的public类型的属性。
     * getDeclaredFields()：获得当前类的所有属性（不包含父类）
     * 
     * @param clazz
     * @throws IllegalAccessException
     */
    private void listFields(Class clazz) throws IllegalAccessException {
        // getFields 是
        // getDeclaredFields 是获取public/private 不包含继承字段的field
        // clazz.getSuperclass().getFields();
        Field[] fields = clazz.getDeclaredFields();
        // Field[] fields= clazz.getFields();
        for (Field f : fields) {
            System.out.println("fK:" + f.getName() + " from:" + f.getClass());
        }
        System.out.println("=======Fields======");
    }

    private void listMethods(Class clazz) {
        // Method[] methods = clazz.getMethods();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m.getName() + " " + m.getAnnotatedReturnType());
        }
        System.out.println("======Method=======");
    }

    private void newInstance(Class clazz, Class... p) throws Exception {
        Object objectCopy = clazz.getConstructor(p.getClass()).newInstance(p);
    }

    @Test
    public void testGetClass() throws Exception {
        Persion p = new Persion();
        Class clazz = p.getClass();
        // listFields(clazz);
        // listMethods(clazz);
        Method singe = clazz.getMethod("singe", String.class);
        String ret = (String) singe.invoke(p, "hello admin");
        System.out.println(ret);
    }

    @Test
    public void getConnect() throws ClassNotFoundException {
        Class.forName("");
    }

    @Test
    public void testClassForName() throws Exception {
        Class clazz = Class.forName("com.test.reflect.Persion");
        listFields(clazz);

    }



}


/**
 * 人类
 */
class Human {
    /**
     * 肤色
     */
    public String color;
    /**
     * 国家
     */
    private String country;
    /**
     * 性别
     */
    private Integer gender;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Human human = (Human) o;

        if (color != null ? !color.equals(human.color) : human.color != null) return false;
        if (country != null ? !country.equals(human.country) : human.country != null) return false;
        if (gender != null ? !gender.equals(human.gender) : human.gender != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    // get set
}


/**
 * 账户 钱包
 */
class Account {
    private String type;// 账户类型，CNY,USD...
    private Double amount = 0d;// bigdecimal

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}


/**
 * 人
 */
class Persion extends Human {
    public String color;
    private String name;
    private String id;
    public String phone = "123";
    private String address;
    private int age = 10;
    List<Account> account;

    public Persion() {
        this.account = Lists.newArrayList();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String singe(String words) {
        return "person singe:" + words;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
