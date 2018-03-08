package com.urbanpawel.overtime;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateTimeUtilsTest {
    private final DateTimeUtils dateTimeUtils = FakeDateTimeUtils.fake2018Valentines2pm();

    @Test
    public void test_14thFebAnd16thFebOf2018_areInTheSameWeek() {
        assertTrue(dateTimeUtils.isCurrentWeek(LocalDate.of(2018, Month.FEBRUARY, 16)));
    }

    @Test
    public void test_14thFebAnd19thFebOf2018_areNotInTheSameWeek() {
        assertFalse(dateTimeUtils.isCurrentWeek(LocalDate.of(2018, Month.FEBRUARY, 19)));
    }

    @Test
    public void test_14thFebAnd31stJanOf2018_areNotInTheSameMonth() {
        assertFalse(dateTimeUtils.isCurrentMonth(LocalDate.of(2018, Month.JANUARY, 31)));
    }

    @Test
    public void test_14thFebAnd19stFabOf2018_areInTheSameMonth() {
        assertTrue(dateTimeUtils.isCurrentMonth(LocalDate.of(2018, Month.FEBRUARY, 19)));
    }

}