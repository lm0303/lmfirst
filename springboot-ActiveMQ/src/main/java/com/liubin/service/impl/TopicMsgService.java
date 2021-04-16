package com.liubin.service.impl;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.liubin.service.MsgService;

@Service
public class TopicMsgService implements MsgService {

    // JMS 消息发送模版
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    @Override
    public void sendMsg(Object msg) {
        jmsMessagingTemplate.convertAndSend(this.topic, msg);
    }

//    @Override
//    @JmsListener(destination = "${my.active.topic}")
//    public void recviceMsg(Object msg) {
//        System.out.println("接收消息为：" + msg);
//        TextMessage textMessage = (TextMessage) msg;
//        try {
//            System.out.println("接收消息为：" + textMessage.getText());
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }


}
