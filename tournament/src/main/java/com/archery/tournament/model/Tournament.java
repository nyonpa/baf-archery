package com.archery.tournament.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable=false)
    private String Name;
    private String location;

    private LocalDate StartDate;
    private LocalDate EndDate;

    @Enumerated(EnumType.STRING)
    private TournamentStatus status = TournamentStatus.UPCOMING;
    @Enumerated(EnumType.STRING)
    private TournamentType tournamentType = TournamentType.BF1;
    @Column(nullable=false)
    private String organizerCid;
    @ElementCollection
    private Set<String> registeredTeams = new HashSet<String>();

}
