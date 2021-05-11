package com.limeng.design.createMode.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtoTypeTest {
    private static Logger logger = LoggerFactory.getLogger(ProtoTypeTest.class);

    public static void main(String[] args) throws CloneNotSupportedException {
        Phone phone = new Phone();
        phone.setName("P40");
        phone.setPrice(4500.00f);
        Persion persion = new Persion();
        persion.setName("郭台铭");
        Factory factory = new Factory();
        factory.setName("华为");
        factory.setManager(persion);
        phone.setFactory(factory);
        Phone phone1 = phone.clone();
        phone1.setName("honor 9x");
        phone1.setPrice(1800.00f);
        logger.info("手机信息：{},{},{}",phone.getName(),phone.getPrice(),phone.getFactory());
        logger.info("手机信息：{},{},{}",phone1.getName(),phone1.getPrice(),phone1.getFactory());
    }
}
