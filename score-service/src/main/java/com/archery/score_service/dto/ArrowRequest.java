package com.archery.score_service.dto;


import com.archery.score_service.model.ArrowType;
import lombok.Data;

@Data
public class ArrowRequest {

    private Long matchId;
    private Long roundId;
    private String archerCid;
    private Long teamId;
    private ArrowType type;
}