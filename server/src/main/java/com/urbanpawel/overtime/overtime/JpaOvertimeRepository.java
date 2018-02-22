package com.urbanpawel.overtime.overtime;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
interface JpaOvertimeRepository extends OvertimeRepository, JpaRepository<OvertimeSummary, Integer> {
    @Override
    default void saveSummary(OvertimeSummary summary) {
        save(summary);
    }

    @Override
    default Optional<OvertimeSummary> summaryFor(LocalDate date) {
        return Optional.ofNullable(findOneByDate(date));
    }

    @Override
    default List<OvertimeSummary> allSummaries() {
        return findAll(new Sort(Sort.Direction.DESC, "date"));
    }

    OvertimeSummary findOneByDate(LocalDate date);
}
