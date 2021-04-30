package com.limeng.design.createMode.singleton;

public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){
        System.out.println("饿汉模式");
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}
