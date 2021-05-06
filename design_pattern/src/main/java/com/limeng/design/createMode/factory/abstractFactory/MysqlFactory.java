package com.limeng.design.createMode.factory.abstractFactory;

public class MysqlFactory implements DBFactory{
    @Override
    public User createUser() {
        return new MysqlUser();
    }

    @Override
    public Dept createDept() {
        return new MysqlDept();
    }
}
