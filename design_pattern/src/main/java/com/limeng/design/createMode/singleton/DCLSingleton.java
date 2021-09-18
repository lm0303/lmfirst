package com.limeng.design.createMode.singleton;

/**
 * 单例模式主要解决的是，⼀个全局使⽤的类频繁的创
 * 建和消费，从⽽提升提升整体的代码的性能
 */
public class DCLSingleton {
    private volatile static DCLSingleton instance;

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
