package com.limeng.design.createMode.builder;

public class Car implements IBuilder{

    private Product product = new Product();

    @Override
    public void buildPart1() {
        product.setPart1("轿车车架组装完成");
    }

    @Override
    public void buildPart2() {
        product.setPart2("轿车发动机组装完成");
    }

    @Override
    public void buildPart3() {
        product.setPart3("轿车轮胎组装完成");
    }

    @Override
    public Product build() {
        return product;
    }
}
