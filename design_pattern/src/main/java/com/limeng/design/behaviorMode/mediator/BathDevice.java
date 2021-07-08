package com.limeng.design.behaviorMode.mediator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BathDevice extends SmartDevice {
    @Override
    public void readyState(String instruction) {
        log.info("淋浴设备已准备" + instruction);
    }

    @Override
    public void operateDevice(String instruction, SmartMediator mediator) {
        log.info("淋浴设备已" + instruction);
        mediator.bath(instruction);
    }
}
