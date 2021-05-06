package com.limeng.design.createMode.factory.abstractFactory;

public interface DBFactory {
    public User createUser();

    public Dept createDept();
}
