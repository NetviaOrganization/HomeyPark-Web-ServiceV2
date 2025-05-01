package com.homeypark.web_service.parkings.application.internal.queryservices;

import com.homeypark.web_service.parkings.domain.model.entities.Schedule;
import com.homeypark.web_service.parkings.domain.model.queries.GetAllScheduleQuery;
import com.homeypark.web_service.parkings.domain.services.ScheduleQueryService;
import com.homeypark.web_service.parkings.infrastructure.persistence.jpa.repositories.ScheduleRepository;

import java.util.List;

public class ScheduleQueryServiceImpl implements ScheduleQueryService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleQueryServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> handle(GetAllScheduleQuery query) {
        return scheduleRepository.findAll();
    }
}
