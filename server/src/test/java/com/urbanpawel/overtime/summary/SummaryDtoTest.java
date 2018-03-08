package com.urbanpawel.overtime.summary;

import com.urbanpawel.overtime.DateTimeUtils;
import com.urbanpawel.overtime.FakeDateTimeUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static java.math.BigDecimal.ZERO;
import static org.junit.Assert.assertEquals;

public class SummaryDtoTest {
    private final DateTimeUtils fakeDateTime = FakeDateTimeUtils.fake2018Valentines2pm();

    @Test
    public void test_emptySummaryCountsAreZeros() {
        assertEquals(SummaryDto.empty(), SummaryDto.create(ZERO, ZERO, ZERO));
    }

    @Test
    public void test_applyItem() {
        SummaryDto filledDto = SummaryDto.empty().applyItem(fakeDateTime,
                new SummaryItem(LocalDate.of(2018, Month.FEBRUARY, 13), BigDecimal.valueOf(4)));

        assertEquals(SummaryDto.create(BigDecimal.valueOf(4), BigDecimal.valueOf(4), BigDecimal.valueOf(4)), filledDto);
    }
}