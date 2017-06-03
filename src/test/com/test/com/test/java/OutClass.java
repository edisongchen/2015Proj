package com.test.com.test.java;

/**
 * Created by ctg on 2017/2/18.
 */
public class OutClass {
    private String name;
    private int age;

    class innerClass{
        public innerClass(){
            name="ccc";
            age=2;
        }
        public void display(){
            System.out.println(name+":"+age);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String args[]){
        OutClass outClass = new OutClass();
        OutClass.innerClass innerClass=outClass.new innerClass();
        innerClass.display();
    }
}
