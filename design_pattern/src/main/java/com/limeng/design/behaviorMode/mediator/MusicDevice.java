package com.limeng.design.behaviorMode.mediator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MusicDevice extends SmartDevice {
    @Override
    public void readyState(String instruction) {
        log.info("音乐设备已准备" + instruction);
    }

    @Override
    public void operateDevice(String instruction, SmartMediator mediator) {
        log.info("音乐设备已" + instruction);
        mediator.music(instruction);
    }
}
