package com.limeng.design.structuralMode.composite;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Subsidiary implements Company {

    private List<Company> children = new ArrayList<Company>();

    protected String name;

    public Subsidiary(String name) {
       this.name = name;
    }

    @Override
    public void add(Company company) {
        children.add(company);
    }

    @Override
    public void remove(Company company) {
        children.remove(company);
    }

    @Override
    public void display(int depth) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < depth; i++) {
            stringBuffer.append("-");
        }
        log.info(stringBuffer.toString()+this.name);
        for (Company child : children) {
            child.display(depth + 2);
        }
    }

    @Override
    public void duty() {
        for (Company child : children) {
            child.duty();
        }
    }
}
