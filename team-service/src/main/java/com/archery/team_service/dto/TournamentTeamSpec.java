package com.archery.team_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TournamentTeamSpec {

    private Long tournamentId;
    private Integer archerNumber;
    private Integer seedNumber;

}
