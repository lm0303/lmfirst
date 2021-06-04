package com.limeng.design.structuralMode.facade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FacadeTest {
    public static void main(String[] args) {
        Fund fund = new Fund();
        fund.buy();
        log.info("-------------");
        fund.sell();
    }
}
