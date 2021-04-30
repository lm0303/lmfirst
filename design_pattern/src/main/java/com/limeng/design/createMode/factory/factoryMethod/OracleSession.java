package com.limeng.design.createMode.factory.factoryMethod;

public class OracleSession implements Session{
    public OracleSession() {
        System.out.println("创建oracle的session");
    }

    @Override
    public void getContext() {
        System.out.println("获取Oracle的session上下文");
    }
}
