package com.archery.team_service.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateTeam {
    private String teamName;
    private String captainCid;
    private Long tournamentId;
    private Integer maxArchers;
    private Integer maxSeededArchers;
}
