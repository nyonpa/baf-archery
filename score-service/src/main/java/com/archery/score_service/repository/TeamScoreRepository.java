package com.archery.score_service.repository;

import com.archery.score_service.model.TeamScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamScoreRepository extends JpaRepository<TeamScore, Long> {
   Optional<TeamScore> findByMatchIdAndTeamId(Long matchId, String teamId);

    List<TeamScore> findByMatchId(Long matchId);
}
