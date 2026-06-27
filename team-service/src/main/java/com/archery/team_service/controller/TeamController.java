package com.archery.team_service.controller;
import com.archery.team_service.dto.CreateTeam;
import com.archery.team_service.dto.JoinTeamRequest;
import com.archery.team_service.dto.TeamResponse;
import com.archery.team_service.model.Team;
import com.archery.team_service.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@PreAuthorize("hasAnyRole('ROLE_TEAM_CAPTAIN','ROLE_ADMIN')")
public class TeamController {

    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }
    //CREATE TEAM FOR TOURNAMENT
    @PostMapping("/create")
    public ResponseEntity<TeamResponse> create(
            @RequestBody CreateTeam req
    )
    {
        System.out.println("id "+req.getCaptainCid());
        return ResponseEntity.ok( new TeamResponse(service.createTeam(req)));

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
    @GetMapping("/teamExist/{id}")
    public boolean teamExist(@PathVariable Long id) {

        return service.teamExists(id);
    }

}