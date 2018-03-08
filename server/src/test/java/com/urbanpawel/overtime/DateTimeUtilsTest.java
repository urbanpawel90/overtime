package com.urbanpawel.overtime;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DateTimeUtils.class)
public class DateTimeUtilsTest {
    @Before
    public void setUp() {
        PowerMockito.spy(DateTimeUtils.class);
        when(DateTimeUtils.now())
                .thenReturn(LocalDateTime.of(2018, Month.FEBRUARY, 14, 14, 0));
    }

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