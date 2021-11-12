package com.example.principle.dip;

public class Client {
    private TestListener testListener;
    public void setTestListener(TestListener testListener) {
        this.testListener = testListener;
    }

    public void method(){
        this.testListener.test();
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.setTestListener(new TestListenerImpl());
        client.method();
    }
}
