package com.test.java8;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * 引用静态方法
 * 类名::方法名
 */
public class StaticFunctionTest {
    //    引用静态方法	ContainingClass::staticMethodName
//    引用某个对象的实例方法	containingObject::instanceMethodName
//    引用某个类型的任意对象的实例方法	ContainingType::methodName
//    引用构造方法	ClassName::new
    Person[] persons = null;

    @Before
    public void before() {
        persons = new Person[10];
        for (int i = 0; i < 10; i++) {
            Person p = new Person();
            p.setName("name" + i);
            p.setBirthday(new Random().nextInt(20));
            p.setEmailAddress("email" + i);
            p.setGender(Person.Sex.FEMALE);
            persons[i] = p;
            System.out.println(p);
        }
    }


    @After
    public void after() {
        System.out.println("//////////after////////////");
        for (int i = 0; i < 10; i++) {
            System.out.println(persons[i]);
        }
    }

    @Test
    public void tradition() {
        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getBirthday().compareTo(o2.getBirthday());
            }
        });


    }

    @Test
    public void lambda() {
//        Arrays.sort(persons,(o1,o2)->o1.birthday.compareTo(o2.birthday));
        Arrays.sort(persons, Person::compareByAge);

    }

    @Test
    public void objectFun() {
        //方法引用类型
        String[] strArray = {"a", "c", "b"};
        Arrays.sort(strArray, String::compareToIgnoreCase);
    }

    @Test
    public void construct() {
        //引用构造方法
        PersonFactory<Person> p = Person::new;
        Person person = p.create();
        System.out.println(p);
    }

}

@FunctionalInterface
interface PersonFactory<P extends Person> {
    P create();
}

class Person {
    public enum Sex {
        MALE, FEMALE
    }

    String name;
    Integer birthday;
    Sex gender;
    String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public Sex getGender() {
        return gender;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Person() {
    }

    public Person(String name, Integer birthday, Sex gender, String emailAddress) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }
}