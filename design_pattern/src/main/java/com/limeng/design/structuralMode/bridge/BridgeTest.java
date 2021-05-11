package com.limeng.design.structuralMode.bridge;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class BridgeTest {
    public static void main(String[] args) {
        log.info("微信人脸支付测试");
        Pay wxPay = new WXPay(new PayFaceMode());
        wxPay.transfer("weixin","10001",new BigDecimal(100));
        log.info("支付宝指纹支付");
        Pay zfbPay = new ZFBPay(new PayFingerprintMode());
        zfbPay.transfer("zhifubao","20001",new BigDecimal(100));
    }
}
