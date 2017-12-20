package com.urbanpawel.overtime.overtime;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface JpaOvertimeRepository extends OvertimeRepository, JpaRepository<OvertimeSummary, Integer> {
    @Override
    default void saveSummary(OvertimeSummary summary) {
        save(summary);
    }

    @Override
    default Optional<OvertimeSummary> summaryFor(LocalDate date) {
        return Optional.ofNullable(findOneByDate(date));
    }

    OvertimeSummary findOneByDate(LocalDate date);
}
