package com.limeng.design.behaviorMode.command;

public class JiangSuCuisine implements ICuisine{
    private ICook cook;

    public JiangSuCuisine(ICook cook) {
        this.cook = cook;
    }

    @Override
    public void cook() {
        cook.doCooking();
    }
}
