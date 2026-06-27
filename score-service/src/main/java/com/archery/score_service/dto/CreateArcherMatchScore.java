package com.archery.score_service.dto;

import com.archery.score_service.model.RoundScore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class CreateArcherMatchScore {
    private Long matchId;
    private Long teamId;
    private String archerCid;
    private List<RoundScore> roundScores;
}
