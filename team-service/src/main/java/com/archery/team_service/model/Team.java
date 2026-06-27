package com.archery.team_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Team {

    @Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

    @Column(unique=true, nullable=false)
    private String teamName;
    @Column(nullable=false)
    private String captainCid;
    @ElementCollection
    @CollectionTable(
            name ="team_archers",
            joinColumns = @JoinColumn(name="team_id")
    )
    @Column(name="archer_cid")
    private Set<String> archerCid = new HashSet<>();
    private Long tournamentId;
    private Integer maxArchers;
    private Integer maxSeededArchers;
}
