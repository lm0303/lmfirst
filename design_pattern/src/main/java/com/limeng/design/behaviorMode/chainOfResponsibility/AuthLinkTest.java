package com.limeng.design.behaviorMode.chainOfResponsibility;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.Date;

@Slf4j
public class AuthLinkTest {
    public static void main(String[] args) throws ParseException {
        AuthLink authLink = new Level3AuthLink("1000013", "王⼯")
                        .appendNext(new Level2AuthLink("1000012", "张经理")
                        .appendNext(new Level1AuthLink("1000011", "段总")));
        log.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("⼩傅哥","1000998004813441", new Date())));
        // 模拟三级负责⼈审批
        AuthService.auth("1000013", "1000998004813441");
        log.info("测试结果：{}", "模拟三级负责⼈审批，王⼯");
        log.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("⼩傅哥", "1000998004813441", new Date())));
        // 模拟⼆级负责⼈审批
        AuthService.auth("1000012", "1000998004813441");
        log.info("测试结果：{}", "模拟⼆级负责⼈审批，张经理");
        log.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("⼩傅哥", "1000998004813441", new Date())));
        // 模拟⼀级负责⼈审批
        AuthService.auth("1000011", "1000998004813441");
        log.info("测试结果：{}", "模拟⼀级负责⼈审批，段总");
        log.info("测试结果：{}", JSON.toJSONString(authLink.doAuth("⼩傅哥", "1000998004813441", new Date())));
    }
}
