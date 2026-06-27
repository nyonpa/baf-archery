package com.archery.team_service.dto;

import com.archery.team_service.model.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
public class TeamResponse {
    private long id;

    private String teamName;
    private String captainCid;
    private Set<String> archerCid;
    private Long tournamentId;
    private Integer maxArchers;
    private Integer maxSeededArchers;

    public TeamResponse(Team team) {
        this.id = team.getId();
        this.teamName = team.getTeamName();
        this.captainCid = team.getCaptainCid();
        this.archerCid = team.getArcherCid();
        this.tournamentId = team.getTournamentId();
        }
}
