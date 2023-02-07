package com.zeropepsi.seat.repository;

import com.zeropepsi.seat.domain.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

	Theater findByNameAndFacility_Name(String name, String facilityName);

}
