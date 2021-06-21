package com.limeng.design.behaviorMode.chainOfResponsibility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private static Map<String, Date> authMap = new HashMap<>();

    public static Date queryAuthInfo(String uId,String orderId){
        return authMap.get(uId.concat(orderId));
    }

    public static void auth(String uId,String orderId){
        authMap.put(uId.concat(orderId),new Date());
    }
}
