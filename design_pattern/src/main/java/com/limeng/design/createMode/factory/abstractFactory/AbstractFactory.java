package com.limeng.design.createMode.factory.abstractFactory;

public class AbstractFactory {
    public static void main(String[] args) {
        MysqlFactory dbFactory = new MysqlFactory();
        dbFactory.createUser().insert();
        dbFactory.createDept().insert();
        new OracleFactory().createUser().insert();
    }
}
