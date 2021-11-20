package com.example.pattern.builder;

public class TestBuilder {
    private Test test;
    public TestBuilder() {
        test = new Test();
    }

    public TestBuilder setTitle(String title){
        test.title = title;
        return this;
    }

    public TestBuilder setMessage(String message){
        test.message = message;
        return this;
    }

    public Test build(){
        System.out.println("TestBuilder:: TITLE is "+test.title+"\n"+"MESSAGE is "+test.message);
        return test;
    }
}
