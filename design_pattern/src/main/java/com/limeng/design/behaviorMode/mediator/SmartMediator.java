package com.limeng.design.behaviorMode.mediator;

public abstract class SmartMediator {
    SmartDevice bd;
    SmartDevice cd;
    SmartDevice md;

    public SmartMediator(SmartDevice bd, SmartDevice cd, SmartDevice md) {
        this.bd = bd;
        this.cd = cd;
        this.md = md;
    }
    public abstract void music(String instruction);

    public abstract void curtain(String instruction);

    public abstract void bath(String instruction);
}
