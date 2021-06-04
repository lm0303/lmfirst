package com.limeng.design.structuralMode.facade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stock2 {
    public void buy(){
        log.info("股票2买入");
    }
    public void sell(){
        log.info("股票2卖出");
    }
}
