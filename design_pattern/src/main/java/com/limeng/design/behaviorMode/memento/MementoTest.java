package com.limeng.design.behaviorMode.memento;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class MementoTest {
    public static void main(String[] args) {
        Admin admin = new Admin();
        ConfigOriginator configOriginator = new ConfigOriginator();
        configOriginator.setConfigFile(new ConfigFile("10001","配置内容1",new Date(),"lm01"));
        admin.append(configOriginator.saveMemento());
        configOriginator.setConfigFile(new ConfigFile("10002","配置内容2",new Date(),"lm01"));
        admin.append(configOriginator.saveMemento());
        configOriginator.setConfigFile(new ConfigFile("10003","配置内容3",new Date(),"lm01"));
        admin.append(configOriginator.saveMemento());
        configOriginator.setConfigFile(new ConfigFile("10004","配置内容4",new Date(),"lm01"));
        admin.append(configOriginator.saveMemento());

        configOriginator.getMemento(admin.undo());
        log.info("历史配置（回滚）undo:{}", JSON.toJSONString(configOriginator.getConfigFile()));
        configOriginator.getMemento(admin.undo());
        log.info("历史配置（回滚）undo:{}", JSON.toJSONString(configOriginator.getConfigFile()));
        configOriginator.getMemento(admin.redo());
        log.info("历史配置（前进）undo:{}", JSON.toJSONString(configOriginator.getConfigFile()));
        configOriginator.getMemento(admin.get("10001"));
        log.info("历史配置（获取）undo:{}", JSON.toJSONString(configOriginator.getConfigFile()));

    }
}
