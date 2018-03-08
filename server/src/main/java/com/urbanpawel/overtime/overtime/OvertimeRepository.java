package com.urbanpawel.overtime.overtime;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
interface OvertimeRepository extends JpaRepository<OvertimeSummary, Integer> {
    @Query("SELECT summary FROM OvertimeSummary summary WHERE date = :date")
    Optional<OvertimeSummary> summaryFor(@Param("date") LocalDate date);
    
    default List<OvertimeSummary> allSummaries() {
        return findAll(new Sort(Sort.Direction.DESC, "date"));
    }
}
