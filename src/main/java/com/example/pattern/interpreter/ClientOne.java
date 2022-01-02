package com.example.pattern.interpreter;

public class ClientOne {
    //规则：Robert 和 John 是男性
    public static AbstractExpression1 getMaleExpression(){
        AbstractExpression1 robert = new TerminalExpression("Robert");
        AbstractExpression1 john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //规则：Julie 是一个已婚的女性
    public static AbstractExpression1 getMarriedWomanExpression(){
        AbstractExpression1 julie = new TerminalExpression("Julie");
        AbstractExpression1 married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        AbstractExpression1 isMale = getMaleExpression();
        AbstractExpression1 isMarriedWoman = getMarriedWomanExpression();
        boolean isMan1 = isMale.interpret("John");
        boolean isMan2 = isMale.interpret("Robert");
        boolean isMan3 = isMale.interpret("Bill");
        boolean isMarriedWomen = isMarriedWoman.interpret("Married Julie");
        System.out.println("John is male? \n"+(isMan1?"Yes":"no"));
        System.out.println("Robert is male? \n"+(isMan2?"Yes":"no"));
        System.out.println("Bill is male? \n"+(isMan3?"Yes":"no"));
        System.out.println("Julie is a married women? \n"+(isMarriedWomen?"Yes":"no"));
    }
}
