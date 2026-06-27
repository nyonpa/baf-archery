package com.archery.score_service.Response;

import com.archery.score_service.model.ArcherMatchScore;
import com.archery.score_service.model.HitType;
import com.archery.score_service.model.RoundScore;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRoundScore {
    private ArcherMatchScore archerMatchScore;
    private Integer roundNumber;
    private HitType arrow1;
    private HitType arrow2;

    public ResponseRoundScore(RoundScore roundScore) {
        this.roundNumber = roundScore.getRoundNumber();
        this.archerMatchScore = roundScore.getArcherMatchScore();
        this.arrow1 = roundScore.getArrow1();
        this.arrow2 = roundScore.getArrow2();

    }
}
