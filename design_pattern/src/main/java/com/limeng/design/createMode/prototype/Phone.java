package com.limeng.design.createMode.prototype;

import lombok.Data;

import java.util.Optional;

@Data
public class Phone implements Cloneable{
    private String name;

    private float price;

    private Factory factory;

    @Override
    protected Phone clone() throws CloneNotSupportedException {
        Phone phone = new Phone();
        String name = Optional.ofNullable(this.name).orElseThrow(() -> new IllegalArgumentException("手机名称为空"));
        Float price = Optional.ofNullable(this.price).orElseThrow(() -> new IllegalArgumentException("价格为空"));
        Factory factory = Optional.ofNullable(this.factory).orElseThrow(() -> new IllegalArgumentException("代工厂为空"));
        phone.setName(name);
        phone.setPrice(price);
        phone.setFactory(factory);
        return phone;
    }
}
