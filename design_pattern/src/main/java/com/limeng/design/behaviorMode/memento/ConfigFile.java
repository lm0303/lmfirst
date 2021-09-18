package com.limeng.design.behaviorMode.memento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 配置信息类
 */
@Setter
@Getter
@AllArgsConstructor
public class ConfigFile {
    private String versionNo;

    private String content;

    private Date dateTime;

    private String operator;

}
