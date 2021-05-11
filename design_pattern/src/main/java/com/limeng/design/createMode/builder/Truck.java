package com.limeng.design.createMode.builder;

public class Truck implements IBuilder{

    private Product product = new Product();

    @Override
    public void buildPart1() {
        product.setPart1("卡车车架组装完成");
    }

    @Override
    public void buildPart2() {
        product.setPart2("卡车发动机组装完成");
    }

    @Override
    public void buildPart3() {
        product.setPart3("卡车轮胎组装完成");
    }

    @Override
    public Product build() {
        return product;
    }
}
