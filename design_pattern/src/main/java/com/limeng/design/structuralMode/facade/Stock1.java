package com.limeng.design.structuralMode.facade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stock1 {
    public void buy(){
       log.info("股票1买入");
    }
    public void sell(){
        log.info("股票1卖出");
    }
}
