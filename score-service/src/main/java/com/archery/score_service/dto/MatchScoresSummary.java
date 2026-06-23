package com.archery.score_service.dto;

import com.archery.score_service.model.ArcherScore;
import com.archery.score_service.model.MatchScore;
import com.archery.score_service.model.TeamScore;
import lombok.Data;

import java.util.List;

@Data
public class MatchScoreSummary {

    private List<ArcherScore> archerScores;

    private List<TeamScore> teamScores;

    private MatchScore matchScore;
}