package com.limeng.design.behaviorMode.command;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JiangSuCook implements ICook{
    @Override
    public void doCooking() {
       log.info("江苏厨师，烹饪苏菜，宫廷第⼆⼤菜系，古今国宴上最受⼈欢迎的菜系");
    }
}
