package com.limeng.design.structuralMode.decorator;


public class SsoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(String request, String response, Object handler){
        String token = request.substring(1,8);
        return "success".equals(token);
    }
}
