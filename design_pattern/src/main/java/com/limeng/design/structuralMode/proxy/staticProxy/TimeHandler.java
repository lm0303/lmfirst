package com.limeng.design.structuralMode.proxy.staticProxy;

import com.limeng.design.structuralMode.proxy.UserServiceTarget;

import java.lang.reflect.Method;
/** 
 * @Param:
 * @return:  
 * @Description: TODO 
 * @throws 
 */ 
public class TimeHandler {
    private UserServiceTarget userServiceTarget = new UserServiceTarget();
    
    public void invoke(Method method) {
        System.out.println("进入了invoke");
        long start = System.nanoTime();
        try {
            method.invoke(userServiceTarget);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("花费时间：" + (end - start));
    }
}
