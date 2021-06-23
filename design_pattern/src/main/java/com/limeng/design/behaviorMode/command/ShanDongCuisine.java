package com.limeng.design.behaviorMode.command;

public class ShanDongCuisine implements ICuisine{
    private ICook cook;

    public ShanDongCuisine(ICook cook) {
        this.cook = cook;
    }

    @Override
    public void cook() {
        cook.doCooking();
    }
}
