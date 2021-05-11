package com.limeng.design.structuralMode.composite;

public class CompsiteTest {
    public static void main(String[] args) {
        Subsidiary root = new Subsidiary("总公司");
        root.add(new HRDept("总公司人力资源部"));
        root.add(new FinanceDept("总公司财务部"));
        Subsidiary sub1 = new Subsidiary("上海分公司");
        sub1.add(new HRDept("上海分公司人力资源部"));
        sub1.add(new FinanceDept("上海分公司财务部"));
        Subsidiary sub2 = new Subsidiary("广州分公司");
        sub2.add(new HRDept("广州分公司人力资源部"));
        sub2.add(new FinanceDept("广州分公司财务部"));
        root.add(sub1);
        root.add(sub2);
        root.display(1);

    }
}
