package com.limeng.design.createMode.prototype;

import lombok.Data;

import java.util.Optional;

@Data
public class Factory implements Cloneable{
    private String name;
    private Persion manager;

    @Override
    protected Factory clone() throws CloneNotSupportedException {
        Factory factory = new Factory();
        String name = Optional.ofNullable(this.name).orElseThrow(()->new IllegalArgumentException("工厂名称为空"));
        factory.setName(name);
        Persion persion = Optional.ofNullable(this.manager).orElseThrow(() -> new IllegalArgumentException("工厂负责人为空"));
        factory.setManager(persion);
        return factory;
    }
}
