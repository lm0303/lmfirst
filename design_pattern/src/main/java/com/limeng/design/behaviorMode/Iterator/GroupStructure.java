package com.limeng.design.behaviorMode.Iterator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GroupStructure implements Collection<Employee, Link> {
    private String groupId;

    private String groupName;
    /**
     * 雇员列表
     */
    private Map<String, Employee> employeeMap = new ConcurrentHashMap<>();
    /**
     * 组织架构关系
     */
    private Map<String, List<Link>> linkMap = new ConcurrentHashMap<>();
    /**
     * 反向关系链
     */
    private Map<String, String> inverteMap = new ConcurrentHashMap<>();

    public GroupStructure(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    @Override
    public boolean add(Employee employee) {
        return null != employeeMap.put(employee.getUId(), employee);
    }

    @Override
    public boolean remove(Employee employee) {
        return null != employeeMap.remove(employee.getUId());
    }

    @Override
    public boolean addLink(String key, Link link) {
        inverteMap.put(link.getToId(), link.getFormId());
        if (linkMap.containsKey(key)) {
            return linkMap.get(key).add(link);
        } else {
            List<Link> links = new LinkedList<>();
            links.add(link);
            linkMap.put(key, links);
            return true;
        }
    }

    @Override
    public boolean removeLink(String key) {
        return null != linkMap.remove(key);
    }

    @Override
    public Iterator<Employee> iterator() {
        return new Iterator<Employee>() {

            Map<String, Integer> keyMap = new HashMap<>();

            int totalIdx = 0;

            private String formId = groupId;

            private String toId = groupId;

            @Override
            public boolean hasNext() {
                return totalIdx < employeeMap.size();
            }

            @Override
            public Employee next() {
                List<Link> links = linkMap.get(toId);
                int cursorIdx = getCursorIdx(toId);
                //同级节点
                if (links == null) {
                    cursorIdx = getCursorIdx(formId);
                    links = linkMap.get(formId);
                }
                //上级节点
                while (cursorIdx > links.size() - 1) {
                    formId = inverteMap.get(formId);
                    cursorIdx = getCursorIdx(formId);
                    links = linkMap.get(formId);
                }
                //获取节点
                Link link = links.get(cursorIdx);
                toId = link.getToId();
                formId = link.getFormId();
                totalIdx++;
                return employeeMap.get(link.getToId());
            }

            /**
             * 给每个层级定义宽度遍历进度
             * @param key
             * @return
             */
            public int getCursorIdx(String key) {
                int idx = 0;
                if (keyMap.containsKey(key)) {
                    idx = keyMap.get(key);
                    keyMap.put(key, ++idx);
                } else {
                    keyMap.put(key, idx);
                }
                return idx;
            }
        };
    }
}
