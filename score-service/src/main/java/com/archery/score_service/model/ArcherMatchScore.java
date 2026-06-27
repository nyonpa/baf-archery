package com.archery.score_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ArcherMatchScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matchId;

    private Long teamId;

    private String archerCid;

    private Integer totalScore;

    private Integer totalHits;

    private Integer bullHits;

    private Integer yellowHits;

    private Integer redHits;

    private Integer normalHits;
    private Integer missHits;
    @OneToMany(
            mappedBy = "archerMatchScore",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RoundScore> roundScores = new ArrayList<>();
}