package com.limeng.design.structuralMode.bridge;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PayFingerprintMode implements IPayMode{
    @Override
    public boolean security(String uid) {
        log.info("指纹支付风控检验");
        return false;
    }
}
