package com.archery.score_service.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private Long tournamentId;

    private Integer totalRounds;

    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.SCHEDULED;


}
