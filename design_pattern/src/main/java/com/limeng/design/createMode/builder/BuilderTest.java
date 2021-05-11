package com.limeng.design.createMode.builder;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuilderTest {

    private static Logger logger = LoggerFactory.getLogger(BuilderTest.class);

    public static void main(String[] args) {
        Product car = new Conductor(new Car()).construct();
        logger.info("产品信息：{},{},{}", car.getPart1(), car.getPart2(),car.getPart3());

        Product truck = new Conductor(new Truck()).construct();
        logger.info("产品信息：{},{},{}", truck.getPart1(), truck.getPart2(),truck.getPart3());
    }
}
