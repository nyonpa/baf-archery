package com.archery.score_service.repository;

import com.archery.score_service.model.RoundScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundScoreRepository extends JpaRepository<RoundScore,Long> {
}
