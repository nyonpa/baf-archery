package com.archery.team_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
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
    @Column(unique=true, nullable=false)
    private String captainCid;
    @ElementCollection
    private Set<String> members = new HashSet<>();
    private int maxMembers = 5;
}
