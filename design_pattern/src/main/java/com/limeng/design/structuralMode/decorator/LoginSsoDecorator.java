package com.limeng.design.structuralMode.decorator;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class LoginSsoDecorator extends SsoDecorator {

    private static Map<String, String> authMap = new ConcurrentHashMap<String, String>();

    static {
        authMap.put("huahua", "queryUserInfo");
        authMap.put("doudou", "queryUserInfo");
    }

    public LoginSsoDecorator(HandlerInterceptor handlerInterceptor) {
        super(handlerInterceptor);
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) {
        boolean success = super.preHandle(request, response, handler);
        if (!success) {
            return false;
        }
        String userid = request.substring(8);
        String method = authMap.get(userid);
        log.info("单点登录方法访问校验拦截：{} {}", userid, method);
        return "queryUserInfo".equals(method);
    }
}
