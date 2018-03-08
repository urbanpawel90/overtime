package com.urbanpawel.overtime.summary;

import com.urbanpawel.overtime.DateTimeUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static java.math.BigDecimal.ZERO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DateTimeUtils.class)
public class SummaryDtoTest {
    @Before
    public void setUp() {
        PowerMockito.spy(DateTimeUtils.class);
        when(DateTimeUtils.now())
                .thenReturn(LocalDateTime.of(2018, Month.FEBRUARY, 14, 14, 0));
    }

    @Test
    public void test_emptySummaryCountsAreZeros() {
        assertEquals(SummaryDto.empty(), SummaryDto.create(ZERO, ZERO, ZERO));
    }

    @Test
    public void test_applyItem() {
        SummaryDto filledDto = SummaryDto.empty().applyItem(
                new SummaryItem(LocalDate.of(2018, Month.FEBRUARY, 13), BigDecimal.valueOf(4)));

        assertEquals(SummaryDto.create(BigDecimal.valueOf(4), BigDecimal.valueOf(4), BigDecimal.valueOf(4)), filledDto);
    }
}