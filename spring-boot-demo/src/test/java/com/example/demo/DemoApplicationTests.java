package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        String str = "string";
        str.intern();
        String str1 = new String("string");
        str1.intern();
        String str2 = new String("string");
        str2.intern();
        System.out.println(str == str1);
        System.out.println(str2 == str1);
    }
    @Test
    void test(){
        Integer a = 1;
        Integer b = 2;
        Long c = 3l;
        System.out.println(c.equals(a+b));
        System.out.println(c == a+b);
    }

}
