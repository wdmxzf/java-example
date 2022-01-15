package com.example.pattern.command;

/**
 * 具体命令角色
 */
public class ConcreteCommand1 implements Command {
    private Receiver receiver;

    public ConcreteCommand1(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * 执行命令
     */
    @Override
    public void execute() {
        // 调用接收者的相关方法来执行具体逻辑
        receiver.action1();
    }
}
