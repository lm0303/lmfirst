package com.limeng.design.createMode.builder;

/**
 * 抽象的产品创建流程
 */
public interface IBuilder {

    void buildPart1();

    void buildPart2();

    void buildPart3();

    Product build();
}
