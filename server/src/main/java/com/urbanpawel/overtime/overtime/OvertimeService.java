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
class OvertimeService {
    private final OvertimeRepository repository;

    @Autowired
    public OvertimeService(OvertimeRepository repository) {
        this.repository = repository;
    }

    public ReportOvertimeSpecification reportForDate(LocalDate date) {
        return new ReportOvertimeSpecification(date, BigDecimal.ZERO);
    }

    @Transactional
    protected void save(ReportOvertimeSpecification specification) {
        OvertimeSummary specSummary = specification.toSummary();
        repository.save(repository.summaryFor(specification.date)
                .map(found -> found.apply(specSummary))
                .orElse(specSummary)
        );
    }

    public OvertimeSummary getSummary(LocalDate date) {
        return repository.summaryFor(date)
                .orElse(OvertimeSummary.emptySummary(date));
    }

    public List<OvertimeSummary> getSummaries() {
        return repository.allSummaries();
    }

    public class ReportOvertimeSpecification {
        private final LocalDate date;
        private final BigDecimal hours;
        private final String comment;

        private ReportOvertimeSpecification(LocalDate date, BigDecimal hours) {
            this(date, hours, null);
        }

        private ReportOvertimeSpecification(LocalDate date, BigDecimal hours, String comment) {
            this.date = date;
            this.hours = hours;
            this.comment = comment;
        }

        public ReportOvertimeSpecification hours(int hours) {
            return hours(BigDecimal.valueOf(hours));
        }

        public ReportOvertimeSpecification hours(BigDecimal hours) {
            return new ReportOvertimeSpecification(date, this.hours.add(hours));
        }

        public void save() {
            if (hours.compareTo(BigDecimal.ZERO) == 0) {
                throw new InvalidParameterException("Can't report zero hours!");
            }
            OvertimeService.this.save(this);
        }

        private OvertimeSummary toSummary() {
            return new OvertimeSummary(hours, date,
                    Collections.singletonList(new OvertimeSummary.Change(hours, LocalDateTime.now(), comment)));
        }

        public ReportOvertimeSpecification comment(String comment) {
            return new ReportOvertimeSpecification(date, hours, comment);
        }
    }
}
