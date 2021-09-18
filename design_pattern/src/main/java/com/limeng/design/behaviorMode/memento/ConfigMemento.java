package com.limeng.design.behaviorMode.memento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 备忘录类
 */
@Setter
@Getter
@AllArgsConstructor
public class ConfigMemento {
    private ConfigFile configFile;
}
