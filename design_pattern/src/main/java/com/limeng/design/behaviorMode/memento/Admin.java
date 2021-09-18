package com.limeng.design.behaviorMode.memento;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理员类
 */
public class Admin {
    private int cursorIdx = 0;
    private List<ConfigMemento> mementoList = new ArrayList<>();
    private Map<String, ConfigMemento> mementoMap = new ConcurrentHashMap<>();

    /**
     * 存放
     * @param configMemento
     */
    public void append(ConfigMemento configMemento) {
        mementoList.add(configMemento);
        mementoMap.put(configMemento.getConfigFile().getVersionNo(), configMemento);
        cursorIdx++;
    }

    /**
     * 回滚
     * @return
     */
    public ConfigMemento undo() {
        if (--cursorIdx < 0) {
            return mementoList.get(0);
        }
        return mementoList.get(cursorIdx);
    }

    /**
     * 返回
     * @return
     */
    public ConfigMemento redo() {
        if (++cursorIdx > mementoList.size()) {
            return mementoList.get(mementoList.size() - 1);
        }
        return mementoList.get(cursorIdx);
    }

    /**
     * 定向获取
     * @param versionNo
     * @return
     */
    public ConfigMemento get(String versionNo) {
        return mementoMap.get(versionNo);
    }
}
