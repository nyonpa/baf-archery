package com.archery.score_service.Response;

import com.archery.score_service.model.Match;
import com.archery.score_service.model.MatchStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMatch {
    private Long tournamentId;
    private LocalDate matchDate;
    private Integer totalRounds;
    private MatchStatus status = MatchStatus.SCHEDULED;
    private Integer qualifyingTeamNumbers;
    private Set<Long> matchParticipantTeam;
    public ResponseMatch(Match match) {
        this.tournamentId = match.getTournamentId();
        this.matchDate = match.getMatchDate();
        this.totalRounds = match.getTotalRounds();
        this.status = match.getStatus();
        this.qualifyingTeamNumbers = match.getQualifyingTeamNumbers();
        this.matchParticipantTeam = match.getParticipatingTeams();
    }
}
