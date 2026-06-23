package com.archery.score_service.controller;

import com.archery.score_service.dto.ArrowRequest;
import com.archery.score_service.model.Arrow;
import com.archery.score_service.model.ArrowType;
import com.archery.score_service.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    // =====================================
    // SUBMIT ARROW (MAIN SCORING ENDPOINT)
    // =====================================
    @PostMapping("/arrow")
    public ResponseEntity<Arrow> submitArrow(@RequestBody ArrowRequest request) {

        Arrow saved = scoreService.submitArrow(
                request.getMatchId(),
                request.getRoundId(),
                request.getArcherCid(),
                request.getTeamId(),
                request.getType()
        );

        return ResponseEntity.ok(saved);
    }

    // =====================================
    // MATCH SUMMARY (ARCHER + TEAM + MATCH)
    // =====================================
    @GetMapping("/match/{matchId}/summary")
    public ResponseEntity<?> getMatchSummary(@PathVariable Long matchId) {

        return ResponseEntity.ok(scoreService.getMatchSummary(matchId));
    }
}