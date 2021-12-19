package com.example.pattern.state;

public class Client {
    /**
     * 执行
     */
    public static void main(String[] args) {
        Controller controller =new Controller();
        controller.on();
        controller.method1();
        controller.method2();
        controller.off();
        controller.method1();
        controller.method2();
    }
}
