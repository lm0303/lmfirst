package com.limeng.design.behaviorMode.Iterator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IteratorTest {
    public static void main(String[] args) {
        // 数据填充
        GroupStructure groupStructure = new GroupStructure("1", "⼩傅哥");

        // 雇员信息
        groupStructure.add(new Employee("2", "花花", "⼆级部⻔"));
        groupStructure.add(new Employee("3", "⾖包", "⼆级部⻔"));
        groupStructure.add(new Employee("4", "蹦蹦", "三级部⻔"));
        groupStructure.add(new Employee("5", "⼤烧", "三级部⻔"));
        groupStructure.add(new Employee("6", "⻁哥", "四级部⻔"));
        groupStructure.add(new Employee("7", "玲姐", "四级部⻔"));
        groupStructure.add(new Employee("8", "秋雅", "四级部⻔"));

        // 节点关系 1->(1,2) 2->(4,5)
        groupStructure.addLink("1", new Link("1", "2"));
        groupStructure.addLink("1", new Link("1", "3"));
        groupStructure.addLink("2", new Link("2", "4"));
        groupStructure.addLink("2", new Link("2", "5"));
        groupStructure.addLink("5", new Link("5", "6"));
        groupStructure.addLink("5", new Link("5", "7"));
        groupStructure.addLink("5", new Link("5", "8"));
        Iterator<Employee> iterator = groupStructure.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            log.info("{}，雇员 Id：{} Name：{}", employee.getDesc(),
                    employee.getUId(), employee.getName());
        }
    }
}
