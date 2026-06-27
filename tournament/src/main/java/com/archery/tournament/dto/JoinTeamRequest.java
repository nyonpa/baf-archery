package com.archery.tournament.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class JoinTeamRequest {
    private long teamId;
}
