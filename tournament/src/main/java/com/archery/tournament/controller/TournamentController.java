package com.archery.tournament.controller;


import com.archery.tournament.dto.CreateTournamentRequest;
import com.archery.tournament.response.TournamentResponse;
import com.archery.tournament.model.Tournament;
import com.archery.tournament.service.TournamentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService service;

    public TournamentController(TournamentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TournamentResponse> create(@RequestBody CreateTournamentRequest req) {
        return ResponseEntity.ok(new TournamentResponse(service.create(req)));
    }

    @GetMapping
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
    public ResponseEntity<TournamentResponse> registerTeam(
            @PathVariable Long id,
            @PathVariable String teamId
    ) {
        return ResponseEntity.ok(
                new TournamentResponse(service.registerTeam(id, teamId))
        );
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<TournamentResponse> start(@PathVariable Long id) {
        return ResponseEntity.ok(
                new TournamentResponse(service.startTournament(id))
        );
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<TournamentResponse> complete(@PathVariable Long id) {
        return ResponseEntity.ok(
                new TournamentResponse(service.completeTournament(id))
        );
    }
}