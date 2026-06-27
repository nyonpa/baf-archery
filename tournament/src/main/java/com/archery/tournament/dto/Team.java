package com.archery.tournament.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class Team {

	private long id;

    private String teamName;
    private String captainCid;
    private List<String> archerCid;
}
