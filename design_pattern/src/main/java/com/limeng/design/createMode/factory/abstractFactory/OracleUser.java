package com.limeng.design.createMode.factory.abstractFactory;

public class OracleUser implements User{
    @Override
    public void insert() {
        System.out.println("插入一个oracle用户");
    }
}
