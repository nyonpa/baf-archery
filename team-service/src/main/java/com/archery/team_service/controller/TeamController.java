package com.archery.team_service.controller;

import com.archery.team_service.dto.CreateTeamRequest;
import com.archery.team_service.dto.JoinTeamRequest;
import com.archery.team_service.model.Team;
import com.archery.team_service.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Team> create(
            @RequestHeader("X-User-Cid") String cid,
            @RequestBody CreateTeamRequest req
    ) {
        return ResponseEntity.ok(service.createTeam(req.getTeamName(), cid));
    }

    @PostMapping("/join")
    public ResponseEntity<Team> join(
            @RequestHeader("X-User-Cid") String cid,
            @RequestBody JoinTeamRequest req
    ) {
        return ResponseEntity.ok(service.joinTeam(cid, req.getTeamId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTeam(id));
    }
}