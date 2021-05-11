package com.limeng.design.structuralMode.composite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FinanceDept extends Subsidiary{
    public FinanceDept(String name) {
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
        log.info("公司财务收支管理{}",name);
    }
}
