package com.limeng.design.structuralMode.flyweight;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteFlyweight extends Flyweight {
    public ConcreteFlyweight(String extrinsic) {
        super(extrinsic);
    }

    @Override
    public void operate(int extrinsic) {
        log.info("具体的Flyweight：" + extrinsic);
    }
}
