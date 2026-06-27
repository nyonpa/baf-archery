package com.archery.score_service.Response;

import com.archery.score_service.model.QualificationStatus;
import com.archery.score_service.model.TeamMatchScore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseTeamMatchScore {
    private Long id;
    private Long matchId;
    private Long teamId;
    private Integer teamScore;
    private QualificationStatus qualified;

    public ResponseTeamMatchScore(TeamMatchScore teamMatchScore) {
        this.id = teamMatchScore.getId();
        this.matchId = teamMatchScore.getMatchId();
        this.teamId = teamMatchScore.getTeamId();
        this.teamScore = teamMatchScore.getTeamScore();
        this.qualified = teamMatchScore.getQualified();
    }
}
