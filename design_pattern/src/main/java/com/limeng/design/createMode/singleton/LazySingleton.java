package com.limeng.design.createMode.singleton;

public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton(){
        System.out.println("懒汉模式");
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
