package com.archery.score_service.service;

import com.archery.score_service.dto.CreateArcherMatchScore;
import com.archery.score_service.dto.CreateMatch;
import com.archery.score_service.dto.CreateRoundScore;
import com.archery.score_service.dto.CreateTeamMatchScore;
import com.archery.score_service.model.ArcherMatchScore;
import com.archery.score_service.model.Match;
import com.archery.score_service.model.RoundScore;
import com.archery.score_service.model.TeamMatchScore;
import com.archery.score_service.repository.ArcherMatchScoreRepository;
import com.archery.score_service.repository.MatchRepository;
import com.archery.score_service.repository.RoundScoreRepository;
import com.archery.score_service.repository.TeamMatchScoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ScoreService {
    private final MatchRepository matchRepository;
    private final TeamMatchScoreRepository teamMatchScoreRepository;
    private final RoundScoreRepository roundScoreRepository;
    private final ArcherMatchScoreRepository archerMatchScoreRepository;

    public ScoreService(MatchRepository matchRepository,
                        TeamMatchScoreRepository teamMatchScoreRepository,
                        RoundScoreRepository roundScoreRepository,
                        ArcherMatchScoreRepository archerMatchScoreRepository)
    {
        this.matchRepository = matchRepository;
        this.teamMatchScoreRepository = teamMatchScoreRepository;
        this.roundScoreRepository = roundScoreRepository;
        this.archerMatchScoreRepository = archerMatchScoreRepository;
    }

    // CREATE SCORE MATCH
    public Match createScoreMatch(CreateMatch createMatch) {
        Match matchObject = new Match();
        matchObject.setTournamentId(createMatch.getTournamentId());
        matchObject.setMatchDate(createMatch.getMatchDate());
        matchObject.setStatus(createMatch.getStatus());
        matchObject.setTotalRounds(createMatch.getTotalRounds());
        matchObject.setQualifyingTeamNumbers(createMatch.getQualifyingTeamNumbers());
        matchObject.setParticipatingTeams(
                createMatch.getMatchParticipantTeam()!=null
                ? new HashSet<>(createMatch.getMatchParticipantTeam())
                        : new HashSet<>()
        );
        return matchRepository.save(matchObject);
    }
    // CREATE TEAM'S MATCH SCORE
    public TeamMatchScore createTeamMatchScore(CreateTeamMatchScore createTeamMatchScore) {
        TeamMatchScore teamMatchScore = new TeamMatchScore();
        teamMatchScore.setMatchId(createTeamMatchScore.getMatchId());
        teamMatchScore.setTeamId(createTeamMatchScore.getTeamId());
        teamMatchScore.setTeamScore(createTeamMatchScore.getTeamScore());
        teamMatchScore.setQualified(createTeamMatchScore.getQualified());
        return teamMatchScoreRepository.save(teamMatchScore);
    }

    // CREATE ROUND SCORE
    /*
    public RoundScore createRoundScore(CreateRoundScore createRoundScore)
    {
        RoundScore roundScore = new RoundScore();
        roundScore.setRoundNumber(createRoundScore.getRoundNumber());
        roundScore.setArcherMatchScore(createRoundScore.getArcherMatchScore());
        roundScore.setArrow1(createRoundScore.getArrow1());
        roundScore.setArrow2(createRoundScore.getArrow2());
        return roundScoreRepository.save(roundScore);
    }*/

    // CREATE ARCHER MATCH SCORE
    public ArcherMatchScore createArcherMatchScore(CreateArcherMatchScore dto) {

        ArcherMatchScore matchScore = new ArcherMatchScore();

        matchScore.setMatchId(dto.getMatchId());
        matchScore.setTeamId(dto.getTeamId());
        matchScore.setArcherCid(dto.getArcherCid());

        List<RoundScore> rounds = new ArrayList<>();

        if (dto.getRoundScores() != null) {
            for (RoundScore r : dto.getRoundScores()) {

                RoundScore round = new RoundScore();
                round.setRoundNumber(r.getRoundNumber());
                round.setArrow1(r.getArrow1());
                round.setArrow2(r.getArrow2());

                // VERY IMPORTANT: set back-reference
                round.setArcherMatchScore(matchScore);

                rounds.add(round);
            }
        }

        matchScore.setRoundScores(rounds);

        // IMPORTANT: cascade saves everything
        return archerMatchScoreRepository.save(matchScore);
    }




}
