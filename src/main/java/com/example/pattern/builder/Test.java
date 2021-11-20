package com.example.pattern.builder;

public class Test {
    public String title;
    public String message;

    public void showTitle(){
        System.out.println("Test:: title is "+title);
    }
    public void showMessage(){
        System.out.println("Test:: message is "+message);
    }

    public static void main(String[] args) {
        // 方式一，显示TestBuilder的结果
        new TestBuilder().setTitle("建造者1").setMessage("BUILDER1").build();

        // 方式二，显示Test 内的方法
        Test test = new TestBuilder().setTitle("建造者2").setMessage("BUILDER2").build();
        test.showTitle();
        test.showMessage();
    }
}
