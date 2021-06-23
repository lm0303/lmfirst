package com.limeng.design.behaviorMode.command;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Waiter {
    private List<ICuisine> cuisineList = new ArrayList<>();

    public void order(ICuisine iCuisine) {
        cuisineList.add(iCuisine);
    }

    public synchronized void placeOrder() {
        for (ICuisine iCuisine : cuisineList) {
            iCuisine.cook();
        }
        cuisineList.clear();
    }
}
