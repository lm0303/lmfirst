package com.limeng.design.createMode.builder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Conductor {
    private IBuilder builder;

    public Product construct(){
        builder.buildPart1();
        builder.buildPart2();
        builder.buildPart3();
        return builder.build();
    }
}
