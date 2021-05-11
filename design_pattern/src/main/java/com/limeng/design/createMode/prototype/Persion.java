package com.limeng.design.createMode.prototype;

import lombok.Data;

@Data
public class Persion implements Cloneable{
    private String name;

    @Override
    protected Persion clone() throws CloneNotSupportedException {
        Persion persion = new Persion();
        persion.setName(this.name);
        return persion;
    }
}
