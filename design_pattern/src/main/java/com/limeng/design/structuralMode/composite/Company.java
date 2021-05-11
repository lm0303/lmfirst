package com.limeng.design.structuralMode.composite;

public interface Company {

    void add(Company company);

    void remove(Company company);

    void display(int depth);

    void duty();
}