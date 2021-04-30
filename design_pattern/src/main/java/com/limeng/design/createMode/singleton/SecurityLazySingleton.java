package com.limeng.design.createMode.singleton;

public class SecurityLazySingleton {
    private static SecurityLazySingleton instance;

    private SecurityLazySingleton(){
        System.out.println("线程安全的懒汉模式");
    }

    public static synchronized SecurityLazySingleton getInstance() {
        if (instance == null) {
            instance = new SecurityLazySingleton();
        }
        return instance;
    }
}
