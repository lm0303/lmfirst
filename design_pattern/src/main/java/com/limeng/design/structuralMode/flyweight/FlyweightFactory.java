package com.limeng.design.structuralMode.flyweight;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class FlyweightFactory {
    private static HashMap<String, Flyweight> pool = new HashMap();

    public static Flyweight getFlyweight(String extrinsic){
        Flyweight flyweight = null;
        if (pool.containsKey(extrinsic)){
            flyweight = pool.get(extrinsic);
            log.info("已有"+extrinsic+"直接从池中取出---->");
        }else {
            flyweight = new ConcreteFlyweight(extrinsic);
            pool.put(extrinsic,flyweight);
            log.info("创建"+extrinsic+"并从池中取出");
        }
        return flyweight;
    }
}
