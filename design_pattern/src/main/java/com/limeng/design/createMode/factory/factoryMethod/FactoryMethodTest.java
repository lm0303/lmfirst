package com.limeng.design.createMode.factory.factoryMethod;

import java.util.function.Supplier;

public class FactoryMethodTest {
    public static void main(String[] args) {
        Supplier<SessionFactory> supplier = MysqlSessionFactory::new;
        Supplier<Session> session = supplier.get()::getSession;
        session.get().getContext();
//        new MysqlSessionFactory().getSession().getContext();
//        new OracleSessionFactory().getSession().getContext();
    }
}
