package com.archery.score_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ArcherScore {

    @Id
    @GeneratedValue
    private Long id;

    private Long matchId;

    private String archerCid;

    private Integer totalScore;
}