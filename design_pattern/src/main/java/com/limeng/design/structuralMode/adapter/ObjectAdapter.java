package com.limeng.design.structuralMode.adapter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ObjectAdapter implements Target {

    private Adaptee adaptee;

    @Override
    public int outPut22V() {
        return adaptee.outPut220V() / 10;
    }
}
