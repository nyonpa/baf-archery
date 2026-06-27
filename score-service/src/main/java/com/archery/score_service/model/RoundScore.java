package com.archery.score_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RoundScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "archer_match_score_id")
    private ArcherMatchScore archerMatchScore;
    private Integer roundNumber;
    @Enumerated(EnumType.STRING)
    private HitType arrow1;
    @Enumerated(EnumType.STRING)
    private HitType arrow2;
}