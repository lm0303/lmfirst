package com.limeng.design.behaviorMode.mediator;

public class ConcreteMediator extends SmartMediator{

    public ConcreteMediator(SmartDevice bd, SmartDevice cd, SmartDevice md) {
        super(bd, cd, md);
    }

    @Override
    public void music(String instruction) {
        cd.readyState(instruction);
        bd.readyState(instruction);
    }

    @Override
    public void curtain(String instruction) {
        md.readyState(instruction);
        bd.readyState(instruction);
    }

    @Override
    public void bath(String instruction) {
        cd.readyState(instruction);
        md.readyState(instruction);
    }
}
