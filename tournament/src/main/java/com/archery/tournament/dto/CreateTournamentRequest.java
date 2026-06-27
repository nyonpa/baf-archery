package com.archery.tournament.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTournamentRequest {

        private String name;
        private String location;
        private LocalDate StartDate;
        private LocalDate EndDate;
        private String organizerCid;
        private String tournamentType;
}
