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
    private final Map<Integer, OvertimeSummary> stubSummaryStore = new HashMap<>();

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
        overtimeService.save(new ReportOvertimeDto(LocalDate.now(), new BigDecimal("2.5")));
        overtimeService.save(new ReportOvertimeDto(LocalDate.now(), BigDecimal.ONE));

        assertEquals(BigDecimal.valueOf(3.5), overtimeService.getSummary(LocalDate.now()).getHours());
    }

    @Test
    public void test_changeLogContainsAllOperations() {
        overtimeService.save(new ReportOvertimeDto(LocalDate.now(), BigDecimal.ONE));
        overtimeService.save(new ReportOvertimeDto(LocalDate.now(), new BigDecimal("0.5")));

        OvertimeSummary summary = overtimeService.getSummary(LocalDate.now());
        assertEquals(2, summary.getChanges().size());
        assertEquals(BigDecimal.valueOf(0.5), summary.getChanges().get(0).getAmount());
    }

    @Test
    public void test_reportingZeroHoursThrowsInvalidParameterException() {
        exceptionsAssert.expect(InvalidParameterException.class);
        exceptionsAssert.expectMessage("Can't report zero hours!");

        overtimeService.save(new ReportOvertimeDto(LocalDate.now(), BigDecimal.ZERO));
    }

    @Test
    public void test_reportWithComment() {
        overtimeService.save(new ReportOvertimeDto(LocalDate.now(), BigDecimal.ONE, "I'm testing overtime comments"));

        assertEquals("I'm testing overtime comments", overtimeService.getSummary(LocalDate.now())
                .getChanges().get(0).getComment());
    }
}
