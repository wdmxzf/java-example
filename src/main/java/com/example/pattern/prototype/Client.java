package com.example.pattern.prototype;

public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
//        Prototype prototype1 = new Prototype();
//        prototype1.setTitle("Title1");
//        prototype1.setMessage("Message1");
//        Children children = new Children();
//        children.setContent("Content1");
//        prototype1.setChildren(children);
//        System.out.println("prototype1------\n    hashCode = "+prototype1.hashCode()+"\n    title ="+prototype1.getTitle()+"\n    message="+prototype1.getMessage());
//        System.out.println("Children1-------\n    hashCode = "+prototype1.getChildren().hashCode()+"\n    content="+prototype1.getChildren().getContent());
//        Prototype prototype2 = prototype1.clone();
//        System.out.println("prototype2------\n    hashCode = "+prototype2.hashCode()+"\n    title ="+prototype2.getTitle()+"\n    message="+prototype2.getMessage());
//        System.out.println("Children2-------\n    hashCode = "+prototype2.getChildren().hashCode()+"\n    content="+prototype2.getChildren().getContent());
//        prototype2.getChildren().setContent("Content2");
//        System.out.println("prototype11------\n    hashCode = "+prototype1.hashCode()+"\n    title ="+prototype1.getTitle()+"\n   message="+prototype1.getMessage());
//        System.out.println("Children11-------\n    hashCode = "+prototype1.getChildren().hashCode()+"\n    content="+prototype1.getChildren().getContent());
//        System.out.println("prototype22------\n    hashCode = "+prototype2.hashCode()+"\n    title ="+prototype2.getTitle()+"\n   message="+prototype2.getMessage());
//        System.out.println("Children22-------\n    hashCode = "+prototype2.getChildren().hashCode()+"\n    content="+prototype2.getChildren().getContent());
        Prototype2 prototype1 = new Prototype2();
        prototype1.setTitle("Title1");
        prototype1.setMessage("Message1");
        Children children = new Children();
        children.setContent("Content1");
        prototype1.setChildren(children);
        System.out.println("prototype1------\n    hashCode = "+prototype1.hashCode()+"\n    title ="+prototype1.getTitle()+"\n    message="+prototype1.getMessage());
        System.out.println("Children1-------\n    hashCode = "+prototype1.getChildren().hashCode()+"\n    content="+prototype1.getChildren().getContent());
        Prototype2 prototype2 = prototype1.clone();
        System.out.println("prototype2------\n    hashCode = "+prototype2.hashCode()+"\n    title ="+prototype2.getTitle()+"\n    message="+prototype2.getMessage());
        System.out.println("Children2-------\n    hashCode = "+prototype2.getChildren().hashCode()+"\n    content="+prototype2.getChildren().getContent());
        prototype2.getChildren().setContent("Content2");
        System.out.println("prototype11------\n    hashCode = "+prototype1.hashCode()+"\n    title ="+prototype1.getTitle()+"\n   message="+prototype1.getMessage());
        System.out.println("Children11-------\n    hashCode = "+prototype1.getChildren().hashCode()+"\n    content="+prototype1.getChildren().getContent());
        System.out.println("prototype22------\n    hashCode = "+prototype2.hashCode()+"\n    title ="+prototype2.getTitle()+"\n   message="+prototype2.getMessage());
        System.out.println("Children22-------\n    hashCode = "+prototype2.getChildren().hashCode()+"\n    content="+prototype2.getChildren().getContent());
    }
}
