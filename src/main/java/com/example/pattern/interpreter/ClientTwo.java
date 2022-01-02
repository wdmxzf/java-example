package com.example.pattern.interpreter;

public class ClientTwo {
    public static void main(String[] args) {
        Calculator calculator = new Calculator("1 + 2 + 3 - 4");
        System.out.println(calculator.calculate());
    }
}
