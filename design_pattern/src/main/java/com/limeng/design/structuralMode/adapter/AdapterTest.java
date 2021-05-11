package com.limeng.design.structuralMode.adapter;


public class AdapterTest {
    public static void main(String[] args) {
        //类适配器
        new Phone().change(new ClassAdapter());
        //对象适配器
        new Phone().change(new ObjectAdapter(new Adaptee()));
    }
}
