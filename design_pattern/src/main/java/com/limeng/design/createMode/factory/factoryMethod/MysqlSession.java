package com.limeng.design.createMode.factory.factoryMethod;

public class MysqlSession implements Session{
    public MysqlSession() {
        System.out.println("创建了mysql的session");
    }

    @Override
    public void getContext() {
        System.out.println("获取mysql的session上下文");
    }
}
