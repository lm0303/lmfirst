package com.limeng.design.structuralMode.proxy.dynamicProxy;


import com.limeng.design.structuralMode.proxy.UserServiceTarget;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();
    public Object getProxy(Class clazz){
        //设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }
    //实现MethodInterceptor接口方法
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        System.out.println("进入invoke方法");
        long start = System.nanoTime();
        //通过代理类调用父类中的方法
        Object result = proxy.invokeSuper(obj, args);
        long end = System.nanoTime();
        System.out.println("花费时间：" + (end - start));
        return result;
    }

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        //通过生成子类的方式创建代理类
        UserServiceTarget userService = (UserServiceTarget) proxy.getProxy(UserServiceTarget.class);
        userService.b();
        userService.c();
    }
}
