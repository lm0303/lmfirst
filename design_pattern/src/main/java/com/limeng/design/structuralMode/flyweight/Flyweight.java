package com.limeng.design.structuralMode.flyweight;

import lombok.Getter;
import lombok.Setter;

public abstract class Flyweight {

    /**
     * 内部状态
     */
    @Setter
    @Getter
    public String intrinsic;

    /**
     * 外部状态
     */
    public final String extrinsic;

    public Flyweight(String extrinsic) {
        this.extrinsic = extrinsic;
    }

    public abstract void operate(int extrinsic);
}
