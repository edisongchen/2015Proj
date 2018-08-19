package test.aviator;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * Created by eds on 2018/8/19.
 */
public class TestFunction extends AbstractFunction {
    @Override
    public String getName() {
        return "testFun";
    }

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        System.out.println(this.getClass().getSimpleName() + "======before");
//        AviatorObject result = super.call(env, arg1);
        System.out.println(this.getClass().getSimpleName() + "======after");
        return new AviatorDouble(FunctionUtils.getNumberValue(arg1, env));
    }
}
