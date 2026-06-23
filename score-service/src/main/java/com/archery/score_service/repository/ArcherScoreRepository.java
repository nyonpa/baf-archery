package com.archery.score_service.repository;

import com.archery.score_service.model.ArcherScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArcherScoreRepository extends JpaRepository<ArcherScore, Long> {



    Optional<ArcherScore> findByMatchIdAndArcherCid(Long matchId, String archerCid);

    List<ArcherScore> findByMatchId(Long matchId);
}

