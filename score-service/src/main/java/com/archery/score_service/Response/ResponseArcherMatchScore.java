package com.archery.score_service.Response;

import com.archery.score_service.model.ArcherMatchScore;
import com.archery.score_service.model.RoundScore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
public class ResponseArcherMatchScore {
    private Long matchId;
    private Long teamId;
    private String archerCid;
    private Integer totalScore;
    private Integer totalHits;
    private Integer bullHits;
    private Integer yellowHits;
    private Integer redHits;
    private Integer normalHits;
    private Integer missedHits;
    private List<RoundScore> roundScores;
    public ResponseArcherMatchScore(ArcherMatchScore archerMatchScore) {
        this.matchId = archerMatchScore.getMatchId();
        this.teamId = archerMatchScore.getTeamId();
        this.archerCid = archerMatchScore.getArcherCid();
        this.totalScore = archerMatchScore.getTotalScore();
        this.totalHits = archerMatchScore.getTotalHits();
        this.bullHits = archerMatchScore.getBullHits();
        this.yellowHits = archerMatchScore.getYellowHits();
        this.redHits = archerMatchScore.getRedHits();
        this.normalHits = archerMatchScore.getNormalHits();
        this.missedHits = archerMatchScore.getMissHits();
        this.roundScores = archerMatchScore.getRoundScores();
    }
}
