package com.archery.team_service.repository;

import com.archery.team_service.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository  extends JpaRepository<Team,Long> {

   Optional<Team> findByCaptainCid(String captainCid);
}
