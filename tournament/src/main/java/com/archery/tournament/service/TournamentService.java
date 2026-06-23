package com.archery.tournament.service;


import com.archery.tournament.dto.CreateTournamentRequest;
import com.archery.tournament.model.Tournament;
import com.archery.tournament.model.TournamentStatus;
import com.archery.tournament.model.TournamentType;
import com.archery.tournament.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    private TournamentRepository tournamentRepository;
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;

    }

    public Tournament create(CreateTournamentRequest  createTournamentRequest) {
        Tournament tournament = new Tournament();
        tournament.setName(createTournamentRequest.getName());
        tournament.setLocation(createTournamentRequest.getLocation());
        tournament.setStartDate(createTournamentRequest.getStartDate());
        tournament.setEndDate(createTournamentRequest.getEndDate());
        tournament.setOrganizerCid(createTournamentRequest.getOrganizerCid());
        tournament.setTournamentType(TournamentType.BF1);
        tournament.setStatus(TournamentStatus.UPCOMING);
        return tournamentRepository.save(tournament);

    }

    public List<Tournament> getAll() {
        return tournamentRepository.findAll();
    }
    // GET BY ID
    public Tournament getById(Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
    }

    // REGISTER TEAM
    public Tournament registerTeam(Long tournamentId, String teamId) {
        Tournament t = getById(tournamentId);
        t.getRegisteredTeams().add(teamId);
        return tournamentRepository.save(t);
    }

    // START TOURNAMENT
    public Tournament startTournament(Long id) {
        Tournament t = getById(id);
        t.setStatus(TournamentStatus.ONGOING);
        return tournamentRepository.save(t);
    }

    // COMPLETE TOURNAMENT
    public Tournament completeTournament(Long id) {
        Tournament t = getById(id);
        t.setStatus(TournamentStatus.FINISHED);
        return tournamentRepository.save(t);
    }

}
