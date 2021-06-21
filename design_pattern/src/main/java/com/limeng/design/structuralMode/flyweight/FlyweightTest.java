package com.limeng.design.structuralMode.flyweight;

public class FlyweightTest {
    public static void main(String[] args) {
        int extrinsic = 22;
        Flyweight flyweightX = FlyweightFactory.getFlyweight("X");
        flyweightX.operate(++extrinsic);
        Flyweight flyweightY = FlyweightFactory.getFlyweight("Y");
        flyweightY.operate(++extrinsic);
        Flyweight flyweightZ = FlyweightFactory.getFlyweight("Z");
        flyweightZ.operate(++extrinsic);
        Flyweight flyweightX1 = FlyweightFactory.getFlyweight("X");
        flyweightX1.operate(++extrinsic);
        UnsharedConcreteFlyweight unsharedConcreteFlyweight = new UnsharedConcreteFlyweight("X");
        unsharedConcreteFlyweight.operate(++extrinsic);
    }
}
