package com.limeng.design.createMode.factory.abstractFactory;

public class OracleDept implements Dept{
    @Override
    public void insert() {
        System.out.println("向oracle部门表中插入一条记录");
    }
}
