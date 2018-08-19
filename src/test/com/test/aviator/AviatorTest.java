package com.test.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.junit.Assert;
import org.junit.Before;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eds on 2018/8/19.
 * Aviator初体验
 * 4.0.0开始 多行表达式(执行多行)
 * 求值器 多实例 （AviatorEvaluator 是一个全局静态实例）
 */
public class AviatorTest {

    @Before
    public void before() {
//        AviatorEvaluatorInstance instance =  AviatorEvaluator.getInstance().setOption(Options.TRACE_EVAL,true);
    }

    @Test
    public void base() {
        // 任何整数转为Long
        Long longResult = (Long) AviatorEvaluator.exec("1+2+3");
        Assert.assertTrue(longResult.equals(6L));
        try {
            Integer integerResult = (Integer) AviatorEvaluator.exec("1+2+3");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ClassCastException);
        }

        // 任何浮点数 转成Double
        Double doubleResult = (Double) AviatorEvaluator.exec("1+2+3.2");
        Assert.assertTrue(doubleResult.equals(6.2d));
        try {
            Float floatResult = (Float) AviatorEvaluator.exec("1+2+3.2");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ClassCastException);
        }
    }

    @Test
    public void multiLineExec() {
        // 多行表达式 在4.0.0 后加入
        AviatorEvaluator.execute("print('hello world');1+2+3;100-1");
    }

    /**
     * execute 支持传递一个map集合，
     * 前面的变量会 通过集合查找到具体的替换值
     */
    @Test
    public void symbolExecute() {
        //构建map
        String name = "Eds";
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("yourName", name);
        String result = (String) AviatorEvaluator.execute(" 'hello ' + yourName ", env);
        Assert.assertTrue(result.equals("hello Eds"));
        //

    }

    /**
     * v2.2 支持传递变量并执行
     */
    @Test
    public void symbolExec() {
        String name = "Eds";
        String result = (String) AviatorEvaluator.exec(" 'hello ' + yourName ", name);
        Assert.assertTrue(result.equals("hello Eds"));
        // 没匹配到就使用null
        Assert.assertTrue(AviatorEvaluator.exec(" 'hello ' + yourName ").equals("hello null"));
    }

    /**
     * 调用函数
     */
    @Test
    public void execuFunction() {
        //注意数值型返回的是Long
        Assert.assertTrue(AviatorEvaluator.execute("string.length('hello')").equals(5L));
        //支持嵌套
        Assert.assertTrue(AviatorEvaluator.execute("string.contains(\"hello\",string.substring('hello',1,2))").equals(true));
    }


    /**
     * lambda在一定程度上替换了方法
     * lambda 在后面的seq库中配合闭包使用
     */
    @Test
    public void lambda() {
        Assert.assertTrue(AviatorEvaluator.exec("(lambda (x,y) -> x + y end)(x,y)", 1, 2).equals(3L));
        //lambda 闭包
        Assert.assertTrue(AviatorEvaluator
                .exec("(lambda (x) -> lambda(y) -> lambda(z) -> x + y + z end end end)(1)(2)(3)").equals(6L));
    }

    @Test
    public void customerFunction() {
        //注册函数
        AviatorEvaluator.addFunction(new AddFunction());
        Assert.assertTrue(AviatorEvaluator.execute("Add(1, 2)").equals(3d));
        Assert.assertTrue(AviatorEvaluator.execute("Add(Add(1, 2), 100)").equals(103d));
        // 对于参数不定的情况，可继承 AbstractVariadicFunction 实现 variadicCall方法
    }

    class AddFunction extends AbstractFunction {

        @Override
        public String getName() {
            return "Add";
        }

        @Override
        public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
            Number left = FunctionUtils.getNumberValue(arg1, env);
            Number right = FunctionUtils.getNumberValue(arg2, env);
            return new AviatorDouble(left.doubleValue() + right.doubleValue());
        }
    }


    /**
     * 默认 classpath下 aviator_functions.config 文件指定function 类
     * 使用参数 -Dcom.googlecode.aviator.custom_function_config_file=xxxx.config 指定自定义文件路径
     */
    @Test
    public void loadCustomerFunction() {
        //TODO 怎么成了Long类型
        Assert.assertTrue(AviatorEvaluator.execute("testFun(1)").equals(1L));
    }


    /**
     * >、!=、==、>、>=、<、<=
     * 也可以用于String、Pattern、Boolean
     * 编译后可以缓存，也可以交给 Aviator 帮你缓存
     */
    @Test
    public void expression() {
        String expression = "a-(b-c)>100";
        // 编译表达式,并让Aviator缓存
        Expression compiledExp = AviatorEvaluator.compile(expression, true);
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        Assert.assertTrue(result == false);
        env.put("c", 100);
        result = (Boolean) compiledExp.execute(env);
        Assert.assertTrue(result == true);
    }

    @Test
    public void accessObject() throws ParseException {
        final List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add(" world");
        final int[] array = new int[3];
        array[0] = 0;
        array[1] = 1;
        array[2] = 3;
        final Map<String, String> map = new HashMap<String, String>();
        map.put("date", "2018-08-19 16:01:02:03");
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("list", list);
        env.put("array", array);
        env.put("mmap", map);
        Assert.assertTrue(AviatorEvaluator.execute("list[0]+list[1]", env).equals("hello world"));   // hello world
        Assert.assertTrue(AviatorEvaluator.execute("array[0]+array[1]+array[2]", env).equals(4L));  // array[0]+array[1]+array[2]=4
        Assert.assertTrue(AviatorEvaluator.execute("'today is ' + mmap.date ", env).equals("today is 2018-08-19 16:01:02:03"));  // today is Wed Feb 24 17:31:45 CST 2016
    }

    @Test
    public void ifElse(){
        Assert.assertTrue(AviatorEvaluator.exec("a>0? 'yes':'no'", 1).equals("yes"));
    }

    @Test
    public void pattern(){
        String email = "killme2008@gmail.com";
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("email", email);
        String username = (String) AviatorEvaluator.execute("email=~/([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1 : 'unknow' ", env);
        Assert.assertTrue(username.equals("killme2008"));
    }
}
