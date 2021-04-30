package com.limeng.design.createMode.singleton;

public class DCLSingleton {
    private static DCLSingleton instance;

    private DCLSingleton(){
        System.out.println("double check locking 单例模式");
    }

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class){
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}
