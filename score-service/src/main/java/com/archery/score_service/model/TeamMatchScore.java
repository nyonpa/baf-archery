package com.archery.score_service.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TeamMatchScore {

    @Id
    @GeneratedValue
    private Long id;
    private Long matchId;
    private Long teamId;
    private Integer teamScore;
    @Enumerated(EnumType.STRING)
    private QualificationStatus qualified = QualificationStatus.PENDING;

}
