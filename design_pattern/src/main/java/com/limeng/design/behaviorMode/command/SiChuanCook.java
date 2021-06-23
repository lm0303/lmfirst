package com.limeng.design.behaviorMode.command;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SiChuanCook implements ICook{
    @Override
    public void doCooking() {
       log.info("四川厨师，烹饪川菜，中国最有特⾊的菜系，也是⺠间最⼤菜系");
    }
}
