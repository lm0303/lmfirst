package com.limeng.design.createMode.factory.abstractFactory;

public class MysqlUser implements User{
    @Override
    public void insert() {
        System.out.println("插入一个mysql用户");
    }
}
