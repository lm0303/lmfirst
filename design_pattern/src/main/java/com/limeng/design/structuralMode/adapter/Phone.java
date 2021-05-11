package com.limeng.design.structuralMode.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 客户端
 */
@Slf4j
public class Phone {
    public void change(Target target){
        log.info("经过适配器后电压为{}V",target.outPut22V());
    }
}
