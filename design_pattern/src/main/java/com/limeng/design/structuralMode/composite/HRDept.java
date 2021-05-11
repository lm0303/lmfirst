package com.limeng.design.structuralMode.composite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HRDept extends Subsidiary{

    public HRDept(String name) {
        super(name);
    }

    @Override
    public void display(int depth) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < depth; i++) {
            sb.append("-");
        }
        log.info(sb.toString()+name);
    }

    @Override
    public void duty() {
        log.info("员工招聘培训管理{}",name);
    }
}
