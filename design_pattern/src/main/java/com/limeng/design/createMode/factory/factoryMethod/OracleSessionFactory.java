package com.limeng.design.createMode.factory.factoryMethod;

public class OracleSessionFactory implements SessionFactory{
    @Override
    public Session getSession() {
        return OracleSession::new;
    }
}
