package com.limeng.design.createMode.factory.abstractFactory;

public class MysqlDept implements Dept{
    @Override
    public void insert() {
        System.out.println("向mysql部门表中插入一条记录");
    }
}
