package com.urbanpawel.overtime.overtime;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OvertimeServiceTest {
    @Rule
    public ExpectedException exceptionsAssert = ExpectedException.none();

    private OvertimeService overtimeService;
    @Mock
    private OvertimeRepository mockOvertimeRepository;
    private Map<Integer, OvertimeSummary> stubSummaryStore = new HashMap<>();

    @Before
    public void setUp() {
        setUpMockRepository();
        stubSummaryStore.clear();
        overtimeService = new OvertimeService(mockOvertimeRepository);
    }

    private void setUpMockRepository() {
        when(mockOvertimeRepository.save(any(OvertimeSummary.class))).thenAnswer(invocation -> {
            OvertimeSummary savedSummary = invocation.getArgumentAt(0, OvertimeSummary.class);
            stubSummaryStore.put(savedSummary.getId(), savedSummary);
            return savedSummary;
        });
        when(mockOvertimeRepository.allSummaries()).thenReturn(new ArrayList<>(stubSummaryStore.values()));
        when(mockOvertimeRepository.summaryFor(any(LocalDate.class))).thenAnswer(invocation -> stubSummaryStore.values()
                .stream()
                .filter(el -> el.getDate().equals(invocation.getArgumentAt(0, LocalDate.class))).findFirst()
        );
    }

    @Test
    public void test_addedEntriesAreSummed() {
        overtimeService.reportForDate(LocalDate.now()).hours(new BigDecimal("2.5")).save();
        overtimeService.reportForDate(LocalDate.now()).hours(1).save();

        assertEquals(BigDecimal.valueOf(3.5), overtimeService.getSummary(LocalDate.now()).getHours());
    }

    @Test
    public void test_changeLogContainsAllOperations() {
        overtimeService.reportForDate(LocalDate.now()).hours(1).save();
        overtimeService.reportForDate(LocalDate.now()).hours(new BigDecimal("0.5")).save();

        OvertimeSummary summary = overtimeService.getSummary(LocalDate.now());
        assertEquals(2, summary.getChanges().size());
        assertEquals(BigDecimal.valueOf(0.5), summary.getChanges().get(0).getAmount());
    }

    @Test
    public void test_reportingZeroHoursThrowsInvalidParameterException() {
        exceptionsAssert.expect(InvalidParameterException.class);
        exceptionsAssert.expectMessage("Can't report zero hours!");

        overtimeService.reportForDate(LocalDate.now()).save();
    }

    @Test
    public void test_reportWithComment() {
        overtimeService.reportForDate(LocalDate.now()).hours(1).comment("I'm testing overtime comments").save();

        assertEquals("I'm testing overtime comments", overtimeService.getSummary(LocalDate.now())
                .getChanges().get(0).getComment());
    }
}
