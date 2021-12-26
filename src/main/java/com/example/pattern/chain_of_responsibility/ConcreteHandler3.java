package com.example.pattern.chain_of_responsibility;
/**
 * 具体处理者角色
 */
public class ConcreteHandler3 extends Handler {

    /**
     * 具体处理方法
     * @param condition 处理条件
     */
    public void handlerRequest(Object condition) {
        String type = (String) condition;
        if (TYPE_3.equals(type)){
            System.out.println("handlerRequest : ConcreteHandler3");
        }else {
            System.out.println("ConcreteHandler3: next");
            nextHandler.handlerRequest(condition);
        }
    }
}
