package com.archery.score_service.repository;

import com.archery.score_service.model.TeamMatchScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMatchScoreRepository extends JpaRepository<TeamMatchScore, Long> {
}
