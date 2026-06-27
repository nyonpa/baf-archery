package com.archery.score_service.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private Long tournamentId;
    private LocalDate matchDate;
    private Integer totalRounds;
    @ElementCollection
    @CollectionTable(
            name="match_team",
            joinColumns = @JoinColumn(name="match_id")
    )
    @Column(name="team_id")
    private Set<Long> participatingTeams = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.SCHEDULED;
    private Integer qualifyingTeamNumbers;




}
