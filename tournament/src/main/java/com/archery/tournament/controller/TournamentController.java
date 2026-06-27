package com.archery.tournament.controller;


import com.archery.tournament.dto.CreateTournamentRequest;
import com.archery.tournament.dto.TournamentResponse;
import com.archery.tournament.dto.TournamentTeamSpec;
import com.archery.tournament.service.TournamentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")

public class TournamentController {

    private final TournamentService service;

    public TournamentController(TournamentService service)
    {
        this.service = service;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ORGANIZER')")
    public ResponseEntity<TournamentResponse> create(@RequestBody CreateTournamentRequest req)
    {
        return ResponseEntity.ok(new TournamentResponse(service.create(req)));
    }

    @GetMapping("/tourList")
    public ResponseEntity<List<TournamentResponse>> getAll() {
        return ResponseEntity.ok(
                service.getAll().stream().map(TournamentResponse::new).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new TournamentResponse(service.getById(id)));
    }

    @PostMapping("/{id}/register/{teamId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ORGANIZER')")
    public ResponseEntity<TournamentResponse> registerTeam(
            @PathVariable Long id,
            @PathVariable String teamId
    ) {
        return ResponseEntity.ok(
                new TournamentResponse(service.registerTeam(id, teamId))
        );
    }

    @PutMapping("/{id}/start")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ORGANIZER')")
    public ResponseEntity<TournamentResponse> start(@PathVariable Long id) {
        return ResponseEntity.ok(
                new TournamentResponse(service.startTournament(id))
        );
    }
    // set the tournament type
    @PutMapping("/type/{id}/{count}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ORGANIZER')")
    public ResponseEntity<TournamentResponse> updateType(@PathVariable Long id, @PathVariable int count) {
        return ResponseEntity.ok(
                new TournamentResponse(service.setTournamentType(id, count))
        );
    }
    @PutMapping("/{id}/complete")
    public ResponseEntity<TournamentResponse> complete(@PathVariable Long id) {
        return ResponseEntity.ok(
                new TournamentResponse(service.completeTournament(id))
        );
    }
    @GetMapping("/teamSpec/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CAPTAIN')")
    public TournamentTeamSpec getTeamSpec(@PathVariable Long id) {

        return service.getTournamentTeamSpec(id);
    }
}