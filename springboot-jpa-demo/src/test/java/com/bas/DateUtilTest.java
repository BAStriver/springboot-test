package com.bas;

import com.bas.util.DateUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

@SpringBootTest
public class DateUtilTest {

    @Test
    public void calculateTimeDifferenceByCalendarTest() throws ParseException {
        DateUtil.calculateTimeDifferenceByCalendar("2021/04/01");
    }

    @Test
    public void calculateTimeDifferenceByPeriodTest() {
        DateUtil.calculateTimeDifferenceByPeriod(2022, 8, 1);
        System.out.println("--------------------");
        DateUtil.calculateTimeDifferenceByPeriod(2023, 8, 1);
    }

    @Test
    public void calculateTimeDifferenceByDurationTest() {
        DateUtil.calculateTimeDifferenceByDuration(10, 10);
        System.out.println("--------------------");
        DateUtil.calculateTimeDifferenceByDuration(120, 120);
    }

    @Test
    public void calculateTimeDifferenceByChronoUnitTest() {
        DateUtil.calculateTimeDifferenceByChronoUnit();
    }

    @Test
    public void calculateTimeDifferenceBySimpleDateFormatTest() throws ParseException {
        DateUtil.calculateTimeDifferenceBySimpleDateFormat("2022-08-01 12:00", "2022-08-12 12:00");
    }

}
