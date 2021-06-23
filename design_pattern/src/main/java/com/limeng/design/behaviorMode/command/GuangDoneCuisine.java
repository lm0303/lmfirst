package com.limeng.design.behaviorMode.command;

public class GuangDoneCuisine implements ICuisine{
    private ICook cook;

    public GuangDoneCuisine(ICook cook) {
        this.cook = cook;
    }

    @Override
    public void cook() {
        cook.doCooking();
    }
}
