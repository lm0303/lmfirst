package com.limeng.design.behaviorMode.command;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuangDongCook implements ICook{
    @Override
    public void doCooking() {
       log.info("⼴东厨师，烹饪鲁菜，宫廷最⼤菜系，以孔府⻛味为⻰头");
    }
}
