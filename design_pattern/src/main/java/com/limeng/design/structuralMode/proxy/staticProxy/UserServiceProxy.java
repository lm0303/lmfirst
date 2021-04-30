package com.limeng.design.structuralMode.proxy.staticProxy;

import com.limeng.design.structuralMode.proxy.UserService;
import com.limeng.design.structuralMode.proxy.UserServiceTarget;

import java.lang.reflect.Method;

public class UserServiceProxy implements UserService {

    @Override
    public void b() {
        try {
            TimeHandler timeHandler = new TimeHandler();
            Method b = UserServiceTarget.class.getMethod("b");
            timeHandler.invoke(b);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void c() {
        try {
            TimeHandler timeHandler = new TimeHandler();
            Method c = UserServiceTarget.class.getMethod("c");
            timeHandler.invoke(c);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UserServiceProxy proxy = new UserServiceProxy();
        proxy.b();
        proxy.c();
    }
}
