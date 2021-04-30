package com.limeng.design.createMode.factory.factoryMethod;

import java.util.function.Supplier;

public class MysqlSessionFactory implements SessionFactory{

    @Override
    public Session getSession() {
        Supplier<MysqlSession> supplier = MysqlSession::new;
        return supplier.get();
    }
}
