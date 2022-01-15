package com.example.pattern.command;

public class CommandClient {
    public static void main(String[] args) {
        // 定义接收者（执行具体逻辑）
        Receiver receiver =new Receiver();
        // 根据接收者构造具体命令对象
        Command command1 = new ConcreteCommand1(receiver);
        Command command2 = new ConcreteCommand2(receiver);
        // 根据命令对象构造请求对象
        Invoker invoker = new Invoker();
        invoker.setCommand1(command1);
        invoker.setCommand2(command2);
        // 发送命令请求
        invoker.action1();
        invoker.action2();
    }
}
