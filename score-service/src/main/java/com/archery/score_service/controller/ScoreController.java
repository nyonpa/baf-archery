package com.archery.score_service.controller;


import com.archery.score_service.Response.ResponseArcherMatchScore;
import com.archery.score_service.Response.ResponseRoundScore;
import com.archery.score_service.Response.ResponseTeamMatchScore;
import com.archery.score_service.dto.CreateArcherMatchScore;
import com.archery.score_service.dto.CreateMatch;
import com.archery.score_service.Response.ResponseMatch;
import com.archery.score_service.dto.CreateRoundScore;
import com.archery.score_service.dto.CreateTeamMatchScore;
import com.archery.score_service.service.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/createMatch")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ORGANIZER')")
    public ResponseEntity<ResponseMatch> createMatch(@RequestBody
                                                         CreateMatch createMatch)
    {
        return  ResponseEntity.ok(new ResponseMatch(
                scoreService.createScoreMatch(createMatch)
                                   )
                                );
    }
    @PostMapping("/createTeamMatchScore")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ORGANIZER')")
    public ResponseEntity<ResponseTeamMatchScore> createTeamMatchScore(CreateTeamMatchScore createTeamMatchScore)
    {
        return ResponseEntity.ok(new ResponseTeamMatchScore(scoreService.createTeamMatchScore(createTeamMatchScore)));
    }
    /*
    //CREATE ROUND SCORE
    public ResponseEntity<ResponseRoundScore> createRoundScore(CreateRoundScore createRoundScore)
    {
        return ResponseEntity.ok(new ResponseRoundScore(scoreService.createRoundScore(createRoundScore)));
    }

     */
    //CREATE ARCHER MATCH SCORE
    @PostMapping("/createArcherMatchScore")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ORGANIZER')")
    public ResponseEntity<ResponseArcherMatchScore> createArcherMatchScore(CreateArcherMatchScore createArcherMatchScore)
    {
        return ResponseEntity.ok(new ResponseArcherMatchScore(scoreService.createArcherMatchScore(createArcherMatchScore)));
    }
}
