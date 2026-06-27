package com.archery.score_service.dto;


import com.archery.score_service.model.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
public class CreateMatch {
    private Long tournamentId;
    private LocalDate matchDate;
    private Integer totalRounds;
    private MatchStatus status = MatchStatus.SCHEDULED;
    private Integer qualifyingTeamNumbers;
    private Set<Long> matchParticipantTeam;
}
