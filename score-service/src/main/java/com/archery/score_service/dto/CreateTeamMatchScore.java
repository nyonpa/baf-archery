package com.archery.score_service.dto;

import com.archery.score_service.model.QualificationStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeamMatchScore {
    private Long id;
    private Long matchId;
    private Long teamId;
    private Integer teamScore;
    private QualificationStatus qualified;
}
