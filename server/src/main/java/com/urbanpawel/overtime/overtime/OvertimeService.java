package com.urbanpawel.overtime.overtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.function.Consumer;

@Service
public class OvertimeService {
    private final OvertimeRepository repository;

    @Autowired
    public OvertimeService(OvertimeRepository repository) {
        this.repository = repository;
    }

    public ReportOvertimeSpecification forDate(LocalDate date) {
        return new ReportOvertimeSpecification(date, BigDecimal.ZERO);
    }

    @Transactional
    private void save(ReportOvertimeSpecification specification) {
        repository.summaryFor(specification.date)
                .<Consumer<OvertimeSummary.Change>>map(currentSummary ->
                        change -> repository.update(currentSummary.apply(change)))
                .orElse(change -> repository.create(OvertimeSummary.fromChange(change)))
                .accept(specification.toChange());
    }

    public OvertimeSummary summaryFor(LocalDate date) {
        return repository.summaryFor(date)
                .orElse(OvertimeSummary.emptySummary(date));
    }

    public class ReportOvertimeSpecification {
        private final LocalDate date;
        private final BigDecimal hours;

        private ReportOvertimeSpecification(LocalDate date, BigDecimal hours) {
            this.date = date;
            this.hours = hours;
        }

        public ReportOvertimeSpecification hours(int hours) {
            return new ReportOvertimeSpecification(date, this.hours.add(BigDecimal.valueOf(hours)));
        }

        public ReportOvertimeSpecification halfHour() {
            return new ReportOvertimeSpecification(date, this.hours.add(BigDecimal.valueOf(0.5)));
        }

        public void save() {
            if (hours.compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidParameterException("Can't report zero or negative hours!");
            }
            OvertimeService.this.save(this);
        }

        private OvertimeSummary.Change toChange() {
            return new OvertimeSummary.Change(hours, ZonedDateTime.now());
        }
    }
}
