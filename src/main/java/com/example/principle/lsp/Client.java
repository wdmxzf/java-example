package com.example.principle.lsp;

public class Client {
    public void method(Parent child){
        child.method1();
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.method(new Children1());
        client.method(new Children2());
    }
}
