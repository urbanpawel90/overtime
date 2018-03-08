package com.urbanpawel.overtime.summary;

import com.urbanpawel.overtime.DateTimeUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(MockitoJUnitRunner.class)
@PrepareForTest(DateTimeUtils.class)
public class SummaryServiceTest {
    private SummaryService summaryService;
    @Mock
    private OvertimeByDateRepository mockOvertimeRepository;

    @Before
    public void setUp() {
        PowerMockito.spy(DateTimeUtils.class);
        when(DateTimeUtils.now())
                .thenReturn(LocalDateTime.of(2018, Month.FEBRUARY, 14, 14, 0));
        summaryService = new SummaryService(mockOvertimeRepository);
    }

    private void mockSummaryItemSet(Set<SummaryItem> summaryItemSet) {
        when(mockOvertimeRepository.getOvertimesGroupedByDate()).thenReturn(summaryItemSet);
    }

    @Test
    public void test_noOvertime_producesZeroValuesSummary() {
        mockSummaryItemSet(Collections.emptySet());

        assertEquals("SummaryDto(0,0,0)", summaryService.countSummary(), SummaryDto.empty());
    }

    @Test
    public void test_previousMonthEntries_areCountOnlyInTotal() {
        mockSummaryItemSet(Stream.of(
                new SummaryItem(LocalDate.of(2018, Month.JANUARY, 1), BigDecimal.ONE),
                new SummaryItem(LocalDate.of(2018, Month.JANUARY, 4), BigDecimal.ONE),
                new SummaryItem(LocalDate.of(2018, Month.JANUARY, 31), BigDecimal.ONE)
        ).collect(Collectors.toSet()));

        assertEquals("SummaryDto(3,0,0)", summaryService.countSummary(),
                SummaryDto.create(BigDecimal.valueOf(3), BigDecimal.ZERO, BigDecimal.ZERO));
    }

    @Test
    public void test_sameWeekEntries_areCountInTotalWeekAndMonth() {
        mockSummaryItemSet(Stream.of(
                new SummaryItem(LocalDate.of(2018, Month.FEBRUARY, 12), BigDecimal.ONE),
                new SummaryItem(LocalDate.of(2018, Month.FEBRUARY, 16), BigDecimal.ONE),
                new SummaryItem(LocalDate.of(2018, Month.FEBRUARY, 15), BigDecimal.ONE)
        ).collect(Collectors.toSet()));

        assertEquals("SummaryDto(3,3,3)", summaryService.countSummary(),
                SummaryDto.create(BigDecimal.valueOf(3), BigDecimal.valueOf(3), BigDecimal.valueOf(3)));
    }

    @Test
    public void test_sameMonthEntriesButOtherWeek_areCountInTotalAndMonth() {
        mockSummaryItemSet(Stream.of(
                new SummaryItem(LocalDate.of(2018, Month.FEBRUARY, 19), BigDecimal.ONE),
                new SummaryItem(LocalDate.of(2018, Month.FEBRUARY, 21), BigDecimal.ONE),
                new SummaryItem(LocalDate.of(2018, Month.FEBRUARY, 24), BigDecimal.ONE)
        ).collect(Collectors.toSet()));

        assertEquals("SummaryDto(3,0,3)", summaryService.countSummary(),
                SummaryDto.create(BigDecimal.valueOf(3), BigDecimal.ZERO, BigDecimal.valueOf(3)));
    }
}