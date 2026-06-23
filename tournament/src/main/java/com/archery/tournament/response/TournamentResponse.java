package com.archery.tournament.response;
import com.archery.tournament.model.Tournament;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class TournamentResponse {

    private Long id;
    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Set<String> registeredTeams;

    public TournamentResponse(Tournament t) {
        this.id = t.getId();
        this.name = t.getName();
        this.location = t.getLocation();
        this.startDate = t.getStartDate();
        this.endDate = t.getEndDate();
        this.status = t.getStatus().name();
        this.registeredTeams = t.getRegisteredTeams();
    }
}
