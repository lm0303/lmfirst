package com.limeng.java8.study.newFeatures;

import java.time.*;

public class DateAndTimeTest {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间：" + now);
        LocalDate localDate = now.toLocalDate();
        System.out.println("date：" + localDate);
        System.out.println("年" + localDate.getYear() + "月" + localDate.getMonthValue() + "日" + localDate.getDayOfMonth());
        LocalTime localTime = now.toLocalTime();
        System.out.println(localTime);
        System.out.println("时" + localTime.getHour() + "分" + localTime.getMinute() + "秒" + localTime.getSecond());
        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }
}
