package com.test.com.test.java;

import org.junit.Test;

/**
 * Created by ctg on 2017/2/17.
 */
public class VariableTest {


    @Test
    public void convert(){
        double d1=1.0;
        int i=(int)d1;
        System.out.println(i);
        int j=0;
        System.out.println(++j);
    }

    @Test
    public void autoIncrement(){
        int a=3;
        if(a==3 && ((a++)==5)){
            System.out.println("a="+a);
        }else{
            System.out.println("no..");
        }
        int i=1;
        i=i++;
        System.out.println("i:"+i);
    }

    /**
     * 单目乘除为关系 逻辑三目后赋值
     * 单目：单目运算符+ –(负数) ++ -- 等
     * 乘除：算数单目运算符* / % + -
     * 为：位移单目运算符<< >>
     * 关系：关系单目运算符> < >= <= == !=
     * 逻辑：逻辑单目运算符&& || & | ^
     * 三目：三目单目运算符A > B ? X : Y
     * 后：无意义，仅仅为了凑字数
     * 赋值：赋值=
     */
    @Test
    public void priority(){
        int k=5;
        int j=++k +(k+5);
        System.out.println("j:"+j);
    }

    @Test
    public void whileDo(){
        int i=2;
        do{
            System.out.println(i);
            i--;
        }while (i>0);
        int a[]={1,2,3};

    }

    @Test
    public void extends2(){
        Extends ex=new Extend2();

    }

}
