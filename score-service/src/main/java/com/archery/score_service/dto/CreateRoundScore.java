package com.archery.score_service.dto;

import com.archery.score_service.model.ArcherMatchScore;
import com.archery.score_service.model.HitType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoundScore {
    private Integer roundNumber;
    private HitType arrow1;
    private HitType arrow2;
}
