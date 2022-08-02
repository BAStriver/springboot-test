package com.bas.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 使用Calendar对象计算时间差，可以按照需求定制自己的计算逻辑
     */
    public static void calculateTimeDifferenceByCalendar(String strDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = formatter.parse(strDate);

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date);

        int year = c1.get(Calendar.YEAR);
        int oldYear = c2.get(Calendar.YEAR); // 当前日期

        System.out.println("传入的日期与今年的年份差为：" + (year - oldYear));
    }

    /**
     * 计算两个LocalDate对象的时间差
     */
    public static void calculateTimeDifferenceByPeriod(int year, int month, int dayOfMonth) {
        LocalDate today = LocalDate.now();
        System.out.println("现在日期：" + today);
        LocalDate oldDate = LocalDate.of(year, Month.of(month), dayOfMonth);
        System.out.println("输入：" + oldDate);

        Period p = Period.between(oldDate, today);
        System.out.printf("输入的日期距离今天的时间差：%d 年 %d 个月 %d 天\n", p.getYears(), p.getMonths(), p.getDays());
    }

    /**
     * 计算时间差
     */
    public static void calculateTimeDifferenceByDuration(int seconds, int days) {
        Instant inst1 = Instant.now();
        System.out.println("当前的时间：" + inst1);
        Instant inst2 = inst1.plus(Duration.ofSeconds(seconds));     //当前时间+seconds秒后的时间
        System.out.println("当前时间+" + seconds + "秒后的时间：" + inst2);
        Instant inst3 = inst1.plus(Duration.ofDays(days));       //当前时间+days天后的时间
        System.out.println("当前时间+" + days + "天后的时间：" + inst3);

        System.out.println("以毫秒计的时间差：" + Duration.between(inst1, inst2).toMillis());
        System.out.println("以秒计的时间差：" + Duration.between(inst1, inst3).getSeconds());
    }

    public static void calculateTimeDifferenceByChronoUnit() {
        LocalDate startDate = LocalDate.of(2020, Month.MAY, 9); // 2020/05/09
        System.out.println("开始时间：" + startDate);

        LocalDate endDate = LocalDate.of(2024, Month.JANUARY, 26); // 2024/01/26
        System.out.println("结束时间：" + endDate);

        long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("两个时间之间的天数差为：" + daysDiff);
    }

    /**
     * 用SimpleDateFormat计算时间差
     */
    public static void calculateTimeDifferenceBySimpleDateFormat(String fromDate, String toDate) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        /*天数差*/
        Date fromDate1 = simpleFormat.parse(fromDate);
        Date toDate1 = simpleFormat.parse(toDate);
        long from1 = fromDate1.getTime();
        long to1 = toDate1.getTime();
        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
        System.out.println("两个时间之间的天数差为：" + days);

        /*小时差*/
        Date fromDate2 = simpleFormat.parse(fromDate);
        Date toDate2 = simpleFormat.parse(toDate);
        long from2 = fromDate2.getTime();
        long to2 = toDate2.getTime();
        int hours = (int) ((to2 - from2) / (1000 * 60 * 60));
        System.out.println("两个时间之间的小时差为：" + hours);

        /*分钟差*/
        Date fromDate3 = simpleFormat.parse(fromDate);
        Date toDate3 = simpleFormat.parse(toDate);
        long from3 = fromDate3.getTime();
        long to3 = toDate3.getTime();
        int minutes = (int) ((to3 - from3) / (1000 * 60));
        System.out.println("两个时间之间的分钟差为：" + minutes);
    }

}
