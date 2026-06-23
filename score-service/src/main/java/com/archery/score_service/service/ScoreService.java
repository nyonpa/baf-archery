package com.archery.score_service.service;

import com.archery.score_service.dto.MatchScoreSummary;
import com.archery.score_service.model.*;
import com.archery.score_service.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    private final ArrowRepository arrowRepository;
    private final ArcherScoreRepository archerScoreRepository;
    private final TeamScoreRepository teamScoreRepository;
    private final MatchScoreRepository matchScoreRepository;

    public ScoreService(
            ArrowRepository arrowRepository,
            ArcherScoreRepository archerScoreRepository,
            TeamScoreRepository teamScoreRepository,
            MatchScoreRepository matchScoreRepository
    ) {
        this.arrowRepository = arrowRepository;
        this.archerScoreRepository = archerScoreRepository;
        this.teamScoreRepository = teamScoreRepository;
        this.matchScoreRepository = matchScoreRepository;
    }

    // =========================
    // SCORE MAPPING (ENUM-BASED)
    // =========================
    private int calculateScore(ArrowType type) {
        return switch (type) {
            case BULL -> 5;
            case RED -> 4;
            case YELLOW -> 3;
            case OTHER -> 2;
        };
    }

    // =========================
    // VALIDATION (2 arrows rule)
    // =========================
    private void validateArrow(Long roundId, String archerCid) {

        long count = arrowRepository
                .countByRoundIdAndArcherCid(roundId, archerCid);

        if (count >= 2) {
            throw new RuntimeException("Each archer can shoot only 2 arrows per round");
        }
    }

    // =========================
    // SUBMIT ARROW (UPDATED)
    // =========================
    public Arrow submitArrow(
            Long matchId,
            Long roundId,
            String archerCid,
            Long teamId,
            ArrowType type
    ) {

        validateArrow(roundId, archerCid);

        Arrow arrow = new Arrow();

        arrow.setMatchId(matchId);
        arrow.setRoundId(roundId);
        arrow.setArcherCid(archerCid);
        arrow.setTeamId(teamId);

        int arrowNumber = getNextArrowNumber(roundId, archerCid);
        arrow.setArrowNumber(arrowNumber);

        arrow.setType(type);
        arrow.setScore(calculateScore(type));

        Arrow saved = arrowRepository.save(arrow);

        recalculateAllScores(matchId);

        return saved;
    }

    // =========================
    // ARROW NUMBER (1 or 2)
    // =========================
    private int getNextArrowNumber(Long roundId, String archerCid) {

        long count = arrowRepository
                .countByRoundIdAndArcherCid(roundId, archerCid);

        return (int) count + 1;
    }

    // =========================
    // RE-CALC ALL
    // =========================
    private void recalculateAllScores(Long matchId) {
        recalcArcherScores(matchId);
        recalcTeamScores(matchId);
        recalcMatchScore(matchId);
    }

    // =========================
    // ARCHER SCORE
    // =========================
    private void recalcArcherScores(Long matchId) {

        List<Object[]> data = arrowRepository.sumByArcher(matchId);

        for (Object[] row : data) {

            String archerCid = (String) row[0];
            Integer total = ((Number) row[1]).intValue();

            ArcherScore score = archerScoreRepository
                    .findByMatchIdAndArcherCid(matchId, archerCid)
                    .orElse(new ArcherScore());

            score.setMatchId(matchId);
            score.setArcherCid(archerCid);
            score.setTotalScore(total);

            archerScoreRepository.save(score);
        }
    }

    // =========================
    // TEAM SCORE
    // =========================
    private void recalcTeamScores(Long matchId) {

        List<Object[]> data = arrowRepository.sumByTeam(matchId);

        for (Object[] row : data) {

            String teamId = (String) row[0];
            Integer total = ((Number) row[1]).intValue();

            TeamScore score = teamScoreRepository
                    .findByMatchIdAndTeamId(matchId, teamId)
                    .orElse(new TeamScore());

            score.setMatchId(matchId);
            score.setTeamId(Long.valueOf(teamId));
            score.setTotalScore(total);

            teamScoreRepository.save(score);
        }
    }

    // =========================
    // MATCH SCORE
    // =========================
    private void recalcMatchScore(Long matchId) {

        Integer total = arrowRepository.sumMatchScore(matchId);

        MatchScore score = matchScoreRepository
                .findByMatchId(matchId)
                .orElse(new MatchScore());

        score.setMatchId(matchId);
        score.setTotalScore(total);

        matchScoreRepository.save(score);
    }

    // =========================
    // MATCH SUMMARY
    // =========================
    public MatchScoreSummary getMatchSummary(Long matchId) {

        MatchScoreSummary summary = new MatchScoreSummary();

        summary.setArcherScores(archerScoreRepository.findByMatchId(matchId));
        summary.setTeamScores(teamScoreRepository.findByMatchId(matchId));

        summary.setMatchScore(
                matchScoreRepository.findByMatchId(matchId).orElse(null)
        );

        return summary;
    }
}