package com.archery.score_service.repository;

import com.archery.score_service.model.MatchScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchScoreRepository extends JpaRepository<MatchScore, Long> {
    Optional<MatchScore> findByMatchId(Long matchId);
}
