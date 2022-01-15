package com.example.pattern.command;

/**
 * 请求者角色
 */
public class Invoker {
    private Command command1;
    private Command command2;

    /**
     * 执行请求方法
     */
    public void action1(){
        // 调用具体命令对象的相关方法，执行具体命令
        command1.execute();
    }

    public void action2(){
        // 调用具体命令对象的相关方法，执行具体命令
        command2.execute();
    }

    public Command getCommand1() {
        return command1;
    }

    public void setCommand1(Command command1) {
        this.command1 = command1;
    }

    public Command getCommand2() {
        return command2;
    }

    public void setCommand2(Command command2) {
        this.command2 = command2;
    }
}
