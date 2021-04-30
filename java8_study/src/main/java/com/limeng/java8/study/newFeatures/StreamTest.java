package com.limeng.java8.study.newFeatures;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        strings.parallelStream().filter(str -> !str.isEmpty()).collect(Collectors.toList()).forEach(System.out::println);
        Stream<String> stream = strings.stream().filter(str -> !str.isEmpty());
        System.out.println("列表中不为空的字符串数量为" + strings.stream().filter(str -> !str.isEmpty()).count());
        stream.collect(Collectors.toList()).forEach(System.out::println);
        String joinStr = strings.stream().filter(str -> !str.isEmpty()).collect(Collectors.joining(","));
        System.out.println(joinStr);

        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        numbers.stream().map(i->i*i).distinct().collect(Collectors.toList()).forEach(System.out::println);

        IntSummaryStatistics stats = numbers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());

    }
}
