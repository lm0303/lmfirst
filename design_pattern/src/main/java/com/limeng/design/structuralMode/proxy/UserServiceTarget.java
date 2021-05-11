package com.limeng.design.structuralMode.proxy;

public class UserServiceTarget implements UserService {

    @Override
    public void b() {
        System.out.println("b");
    }

    @Override
    public void c() {
        System.out.println("c");
    }
}
