package com.limeng.design.behaviorMode.mediator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CurtainDevice extends SmartDevice {
    @Override
    public void readyState(String instruction) {
        log.info("窗帘设备已准备" + instruction);
    }

    @Override
    public void operateDevice(String instruction, SmartMediator mediator) {
        log.info("窗帘已" + instruction);
        mediator.curtain(instruction);
    }
}
