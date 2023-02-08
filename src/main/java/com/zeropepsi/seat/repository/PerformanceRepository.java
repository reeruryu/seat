package com.zeropepsi.seat.repository;

import com.zeropepsi.seat.domain.Performance;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {

	boolean existsByMt20id(String mt20id);

	@Modifying
	@Query("DELETE FROM Performance p WHERE p.prfpdto > :nowDate")
	void deleteByEndDate(@Param("nowDate") LocalDate nowDate);

}
