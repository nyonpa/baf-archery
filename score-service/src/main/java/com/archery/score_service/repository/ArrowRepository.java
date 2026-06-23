package com.archery.score_service.repository;

import com.archery.score_service.model.Arrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArrowRepository extends JpaRepository<Arrow, Integer> {
    long countByRoundIdAndArcherCid(Long roundId, String archerCid);

    List<Object[]> sumByArcher(Long matchId);

    List<Object[]> sumByTeam(Long matchId);

    Integer sumMatchScore(Long matchId);
}
