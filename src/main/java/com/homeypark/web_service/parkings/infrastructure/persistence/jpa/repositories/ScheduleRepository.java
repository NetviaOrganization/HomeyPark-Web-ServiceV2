package com.homeypark.web_service.parkings.infrastructure.persistence.jpa.repositories;

import com.homeypark.web_service.parkings.domain.model.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
