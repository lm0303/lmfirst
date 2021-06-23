package com.limeng.design.behaviorMode.command;

public class SiChuanCuisine implements ICuisine{
    private ICook cook;

    public SiChuanCuisine(ICook cook) {
        this.cook = cook;
    }

    @Override
    public void cook() {
        cook.doCooking();
    }
}
