package com.archery.score_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateMatchRequest {

    private Long tournamentId;
    private List<Long> teamIds;
}
