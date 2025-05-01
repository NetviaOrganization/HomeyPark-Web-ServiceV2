package com.homeypark.web_service.user.infrastructure.repositories.jpa;

import com.homeypark.web_service.user.domain.model.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
