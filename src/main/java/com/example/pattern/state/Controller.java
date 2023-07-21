package com.example.pattern.state;

/**
 * 环境类
 */
public class Controller implements State {
    private State state;

    private void setState(State state) {
        this.state = state;
    }

    public void on(){
        this.setState(new ConcreteStateA());
    }

    public void off(){
        this.setState(new ConcreteStateB());
    }

    public void method1() {
        this.state.method1();
    }

    public void method2() {
        this.state.method2();
    }
}
