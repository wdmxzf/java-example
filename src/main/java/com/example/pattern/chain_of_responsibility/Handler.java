package com.example.pattern.chain_of_responsibility;

/**
 * 抽象处理者角色
 */
public abstract class Handler {
    public static String TYPE_1 = "HANDLER_1";
    public static String TYPE_2 = "HANDLER_2";
    public static String TYPE_3 = "HANDLER_3";

    // 下一节点对象
    protected Handler nextHandler;

    public Handler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * 处理方法
     * @param condition 处理条件
     */
    public abstract void handlerRequest(Object condition);
}
