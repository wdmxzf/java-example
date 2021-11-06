package com.example.pattern.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

public class Client {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //继承被代理类
        enhancer.setSuperclass(XiaoGang.class);
        //设置回调
        enhancer.setCallback(new ProxyInterceptor());
//        enhancer.setCallback(new MethodInterceptor() {
//            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                // 可以做增强
//                return null;
//            }
//        });
//        enhancer.setCallbackFilter(new CallbackFilter() {
//            public int accept(Method method) {
//                return 0;
//            }
//        });
        //设置代理类对象
        XiaoGang xiaoGang = (XiaoGang) enhancer.create();
        //在调用代理类中方法时会被我们实现的方法拦截器进行拦截
        xiaoGang.submitLawsuit();
        xiaoGang.proof();
        xiaoGang.defend();
        xiaoGang.finish();
    }
}
