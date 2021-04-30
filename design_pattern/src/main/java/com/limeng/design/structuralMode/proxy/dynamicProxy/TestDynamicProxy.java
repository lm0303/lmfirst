package com.limeng.design.structuralMode.proxy.dynamicProxy;

import com.limeng.design.structuralMode.proxy.UserService;
import com.limeng.design.structuralMode.proxy.UserServiceTarget;
import sun.misc.ProxyGenerator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class TestDynamicProxy {
    /** 
     * @Param:  
     * @return:  
     * @Description: TODO 
     * @throws 
     */ 
    public static void main(String[] args) {
        try {
            String userServiceProxy2 = "UserServiceProxy2";
            byte[] bytes = ProxyGenerator.generateProxyClass(userServiceProxy2, new Class[]{UserService.class});
            ClassLoader classLoader = new ClassLoader(){
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    return defineClass(name,bytes,0,bytes.length);
                }
            };
            Class<?> aClass = classLoader.loadClass(userServiceProxy2);
            Constructor<?> constructor = aClass.getConstructor(InvocationHandler.class);
            UserServiceTarget target = new UserServiceTarget();
            UserService proxy = (UserService) constructor.newInstance(new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("进入invoke方法");
                    long start = System.nanoTime();
                    method.invoke(target, args);
                    long end = System.nanoTime();
                    System.out.println("花费时间：" + (end - start));
                    return null;
                }
            });
            proxy.b();
            proxy.c();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
