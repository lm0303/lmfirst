package com.limeng.design.createMode.factory.simpleFactory;

/**
 * jdk8利用函数式实现的简单工厂模式
 */
public class OperationFactory {

    public static MathOperation createOperation(String operate) {
        MathOperation operation = null;
        switch (operate) {
            case "+":
                operation = ((a, b) -> a + b);
                break;
            case "-":
                operation = ((a, b) -> a-b);
                break;
            case "*":
                operation = ((a, b) -> a*b);
                break;
            case "/":
                operation = ((a, b) -> a/b);
                break;
            default:
                break;
        }
        return operation;
    }
    private static int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operate(a, b);
    }

    public static void main(String[] args) {
        System.out.println(operate(10,5,createOperation("+")));
        System.out.println(operate(10,5,createOperation("-")));
        System.out.println(operate(10,5,createOperation("*")));
        System.out.println(operate(10,5,createOperation("/")));
    }
}
