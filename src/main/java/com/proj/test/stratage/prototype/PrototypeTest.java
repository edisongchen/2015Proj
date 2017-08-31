package com.proj.test.stratage.prototype;

import org.junit.Test;

/**
 * Created by eds on 2017/7/30.
 */
public class PrototypeTest {

    @Test
    public void testClone(){
        ConcretePrototype a = new ConcretePrototype();
        a.setAttr("this is a test");
        System.out.println("a "+a.getAttr()+" ,"+a);

        ConcretePrototype aCopy = a.clone();
        System.out.println("是否相等："+a.equals(aCopy));
        System.out.println("aCopy "+aCopy.getAttr()+" ,"+aCopy);
    }
}
