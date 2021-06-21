package com.limeng.design.structuralMode.flyweight;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnsharedConcreteFlyweight extends Flyweight {
    public UnsharedConcreteFlyweight(String extrinsic) {
        super(extrinsic);
    }

    @Override
    public void operate(int extrinsic) {
        log.info("不共享的具体Flyweight：" + extrinsic);
    }
}
