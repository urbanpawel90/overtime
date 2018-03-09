package com.urbanpawel.overtime;

import com.urbanpawel.overtime.test.BasicTestSuite;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateTimeUtilsTest extends BasicTestSuite {
    @Test
    public void test_14thFebAnd16thFebOf2018_areInTheSameWeek() {
        assertTrue(DateTimeUtils.isCurrentWeek(LocalDate.of(2018, Month.FEBRUARY, 16)));
    }

    @Test
    public void test_14thFebAnd19thFebOf2018_areNotInTheSameWeek() {
        assertFalse(DateTimeUtils.isCurrentWeek(LocalDate.of(2018, Month.FEBRUARY, 19)));
    }

    @Test
    public void test_14thFebAnd31stJanOf2018_areNotInTheSameMonth() {
        assertFalse(DateTimeUtils.isCurrentMonth(LocalDate.of(2018, Month.JANUARY, 31)));
    }

    @Test
    public void test_14thFebAnd19stFabOf2018_areInTheSameMonth() {
        assertTrue(DateTimeUtils.isCurrentMonth(LocalDate.of(2018, Month.FEBRUARY, 19)));
    }

}