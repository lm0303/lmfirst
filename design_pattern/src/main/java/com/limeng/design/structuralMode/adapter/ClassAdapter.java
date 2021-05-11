package com.limeng.design.structuralMode.adapter;

public class ClassAdapter extends Adaptee implements Target {
    @Override
    public int outPut22V() {
        return outPut220V() / 10;
    }
}
