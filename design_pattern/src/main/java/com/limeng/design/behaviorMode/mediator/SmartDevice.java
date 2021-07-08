package com.limeng.design.behaviorMode.mediator;

public abstract class SmartDevice {
    public abstract void readyState(String instruction);

    public abstract void operateDevice(String instruction, SmartMediator mediator);
}
