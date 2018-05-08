package AopTest;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by WaterMelon on 2018/5/7.
 */
public class MyInterceptor implements MethodInterceptor {
    private Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("aaaaa");
        method.invoke(this.target,objects);
        System.out.println("bbbbb");
    }
}
