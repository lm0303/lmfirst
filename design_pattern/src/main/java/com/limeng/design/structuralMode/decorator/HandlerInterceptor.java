package com.limeng.design.structuralMode.decorator;

public interface HandlerInterceptor {
    boolean preHandle(String request,String reponse,Object hander);
}
