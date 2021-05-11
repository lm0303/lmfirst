package com.limeng.design.structuralMode.bridge;

import java.math.BigDecimal;

public abstract class Pay {
    protected IPayMode iPayMode;

    public Pay(IPayMode iPayMode) {
        this.iPayMode = iPayMode;
    }

    public abstract String transfer(String uid, String tradeId, BigDecimal amount);
}
