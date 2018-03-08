package com.urbanpawel.overtime.summary;

import com.urbanpawel.overtime.overtime.OvertimeSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

interface OvertimeByDateRepository extends JpaRepository<OvertimeSummary, Integer> {
    @Query("SELECT new com.urbanpawel.overtime.summary.SummaryItem(os.date, os.hours) FROM OvertimeSummary os")
    Set<SummaryItem> getOvertimesGroupedByDate();
}
