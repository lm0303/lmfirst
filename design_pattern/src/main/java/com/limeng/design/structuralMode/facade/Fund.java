package com.limeng.design.structuralMode.facade;

public class Fund {
    private Stock1 stock1;

    private Stock2 stock2;

    public Fund(){
        stock1 = new Stock1();
        stock2 = new Stock2();
    }
    public void buy(){
        stock1.buy();
        stock2.buy();
    }
    public void sell(){
        stock1.sell();
        stock2.sell();
    }
}
