package com.urbanpawel.overtime.overtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;

@Service
class OvertimeService {
    private final OvertimeRepository repository;

    @Autowired
    public OvertimeService(OvertimeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(ReportOvertimeDto summary) {
        if (summary.getHours().equals(BigDecimal.ZERO)) {
            throw new InvalidParameterException("Can't report zero hours!");
        }
        repository.save(repository.summaryFor(summary.getDate())
                .map(found -> found.apply(summary))
                .orElse(OvertimeSummary.emptySummary(summary.getDate()).apply(summary))
        );
    }

    public OvertimeSummary getSummary(LocalDate date) {
        return repository.summaryFor(date)
                .orElse(OvertimeSummary.emptySummary(date));
    }

    public List<OvertimeSummary> getSummaries() {
        return repository.allSummaries();
    }
}
