package com.proj.test.stratage.prototype;

/**
 * Created by eds on 2017/7/30.
 */
public class ConcretePrototype implements Cloneable {

    private String attr;

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }


    public ConcretePrototype clone() {
        Object obj = null;
        try {
            obj = super.clone();
            return (ConcretePrototype) obj;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
