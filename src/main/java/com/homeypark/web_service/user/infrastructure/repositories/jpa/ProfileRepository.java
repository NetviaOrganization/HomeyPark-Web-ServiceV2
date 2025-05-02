package com.homeypark.web_service.user.infrastructure.repositories.jpa;


import com.homeypark.web_service.user.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
