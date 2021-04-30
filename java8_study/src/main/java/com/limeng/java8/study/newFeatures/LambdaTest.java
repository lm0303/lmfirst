package com.limeng.java8.study.newFeatures;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 函数式接口
 */
public class LambdaTest {
    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();

        MathOperation add = Integer::sum;
        MathOperation addition = (int a, int b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        MathOperation division = (int a, int b) -> a / b;
        System.out.println("10 + 5 = " + lambdaTest.operate(10, 5, add));
        System.out.println("10 + 5 = " + lambdaTest.operate(10, 5, addition));
        System.out.println("10 - 5 = " + lambdaTest.operate(10, 5, subtraction));
        System.out.println("10 * 5 = " + lambdaTest.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + lambdaTest.operate(10, 5, division));

        GreetingService greetingService1 = message -> System.out.println("hello" + message);
        GreetingService greetingService2 = (message) -> System.out.println("hello" + message);

        greetingService1.sayMessage("M1");
        greetingService2.sayMessage("M2");

        // 定义字符串数组
        String[] strArr = {"abc", "cd", "abce", "a"};
        // 传统方法
        // 匿名内部类
        Arrays.sort(strArr, new Comparator<String>() {

            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s2.length(), s1.length());
            }
        });
        // 输出排序结果
        for (String s : strArr) {
            System.out.println(s);
        }
        System.out.println("---------------------");
        // Lambda表达式
        Arrays.sort(strArr, (s1, s2) -> Integer.compare(s2.length(), s1.length()));
        // 输出
        for (String s : strArr) {
            System.out.println(s);
        }
    }
}

interface MathOperation {
    int operation(int a, int b);
}

interface GreetingService {
    void sayMessage(String message);
}
