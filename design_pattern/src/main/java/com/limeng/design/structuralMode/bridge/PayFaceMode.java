package com.limeng.design.structuralMode.bridge;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PayFaceMode implements IPayMode{
    @Override
    public boolean security(String uid) {
        log.info("人脸支付风控校验");
        return true;
    }
}
