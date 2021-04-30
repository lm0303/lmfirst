package com.limeng.java8.study.newFeatures;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        Integer value1 = null;
        Optional<Integer> integer = Optional.of(1);
        Optional<Object> empty = Optional.empty();
        Optional.ofNullable(value1).orElseThrow(() -> new RuntimeException("test"));
    }
}
