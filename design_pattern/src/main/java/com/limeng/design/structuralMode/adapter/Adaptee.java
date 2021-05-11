package com.limeng.design.structuralMode.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 被适配类
 */
@Slf4j
public class Adaptee {
    public int outPut220V(){
        log.info("经过适配器前电压为220V");
        return 220;
    }
}
