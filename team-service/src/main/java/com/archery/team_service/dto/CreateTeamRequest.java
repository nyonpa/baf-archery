package com.archery.team_service.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTeamRequest {
    private String teamName;
    private int maxMembers;
}
