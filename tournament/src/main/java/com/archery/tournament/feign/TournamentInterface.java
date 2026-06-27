package com.archery.tournament.feign;

import com.archery.tournament.dto.CreateTeamRequest;
import com.archery.tournament.dto.JoinTeamRequest;
import com.archery.tournament.dto.Team;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("TEAM-SERVICE")
public interface TournamentInterface {

    @GetMapping("/{id}")
    public ResponseEntity<Team> get(@PathVariable Long id) ;
    @GetMapping("/teamExist/{id}")
    public boolean teamExist(@PathVariable Long id);

}
