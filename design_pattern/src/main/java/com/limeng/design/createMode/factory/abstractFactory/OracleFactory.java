package com.limeng.design.createMode.factory.abstractFactory;

public class OracleFactory implements DBFactory{
    @Override
    public User createUser() {
        return new OracleUser();
    }

    @Override
    public Dept createDept() {
        return new OracleDept();
    }
}
