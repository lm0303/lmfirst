package com.liubin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liubin.service.impl.P2PMsgService;
import com.liubin.service.impl.TopicMsgService;

@RestController
public class MsgController {

    @Autowired
    private P2PMsgService p2PMsgService;

    @Autowired
    private TopicMsgService topicMsgService;

    // 发送Queue消息
    @GetMapping("/sendQueueMsg")
    public String sendQueueMsg(String msg) {
        p2PMsgService.sendMsg(msg);
        return "success";
    }

    // 发送Queue消息
    @GetMapping("/sendTopicMsg")
    public String sendTopicMsg(String msg) {
        topicMsgService.sendMsg(msg);
        return "success";
    }
}
