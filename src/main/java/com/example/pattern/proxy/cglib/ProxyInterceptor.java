package com.example.pattern.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyInterceptor implements MethodInterceptor {
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("----- ProxyFactory:::::: intercept  开始 ");
        //执行目标对象的方法
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("----- ProxyFactory:::::: intercept  结束 ");
        return result;
    }
}
