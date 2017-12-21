package com.urbanpawel.overtime.overtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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
        OvertimeSummary specSummary = specification.toSummary();
        repository.saveSummary(repository.summaryFor(specification.date)
                .map(found -> found.apply(specSummary))
                .orElse(specSummary)
        );
    }

    public OvertimeSummary summaryFor(LocalDate date) {
        return repository.summaryFor(date)
                .orElse(OvertimeSummary.emptySummary(date));
    }

    public List<OvertimeSummary> sumarries() {
        return repository.allSummaries();
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

        private OvertimeSummary toSummary() {
            return new OvertimeSummary(hours, date,
                    Collections.singletonList(new OvertimeSummary.Change(hours, LocalDateTime.now())));
        }
    }
}
