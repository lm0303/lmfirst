package com.limeng.design.structuralMode.bridge;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class WXPay extends Pay{
    public WXPay(IPayMode iPayMode) {
        super(iPayMode);
    }

    @Override
    public String transfer(String uid, String tradeId, BigDecimal amount) {
        log.info("模拟微信支付划账开始。uid:{} tradeId:{} amount:{}",uid,tradeId,amount);
        boolean security = iPayMode.security(uid);
        log.info("模拟微信支付风控校验。uid:{} tradeId:{} amount:{}",uid,tradeId,amount);
        if (!security){
            log.info("模拟微信支付划账拦截。uid:{} tradeId:{} amount:{}",uid,tradeId,amount);
            return "001";
        }
        log.info("模拟微信支付划账成功。uid:{} tradeId:{} amount:{}",uid,tradeId,amount);
        return "000";
    }
}
