package com.limeng.design.behaviorMode.memento;

import lombok.Getter;
import lombok.Setter;

/**
 * 记录者类
 */
@Setter
@Getter
public class ConfigOriginator {
    private ConfigFile configFile;

    public ConfigMemento saveMemento(){
        return new ConfigMemento(configFile);
    }

    public void getMemento(ConfigMemento configMemento){
        this.configFile = configMemento.getConfigFile();
    }
}
