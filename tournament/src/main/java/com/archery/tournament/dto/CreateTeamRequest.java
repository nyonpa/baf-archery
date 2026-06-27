package com.archery.tournament.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CreateTeamRequest {
    private String teamName;
    private String captainCid;
    private int maxMembers;
    private List<String> archerCid;
}
