package com.archery.score_service.repository;

import com.archery.score_service.model.ArcherMatchScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArcherMatchScoreRepository extends JpaRepository<ArcherMatchScore, Long> {
}
