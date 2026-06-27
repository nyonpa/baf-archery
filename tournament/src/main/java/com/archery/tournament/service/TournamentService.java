package com.archery.tournament.service;
import com.archery.tournament.dto.CreateTournamentRequest;
import com.archery.tournament.dto.TournamentTeamSpec;
import com.archery.tournament.feign.TournamentInterface;
import com.archery.tournament.model.Tournament;
import com.archery.tournament.model.TournamentStatus;
import com.archery.tournament.model.TournamentType;
import com.archery.tournament.repository.TournamentRepository;
import jakarta.ws.rs.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    TournamentInterface tournamentInterface;
    public TournamentService(TournamentRepository tournamentRepository,
                             TournamentInterface tournamentInterface) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentInterface = tournamentInterface;
    }

    public Tournament create(CreateTournamentRequest  createTournamentRequest) {
        Tournament tournament = new Tournament();
        tournament.setName(createTournamentRequest.getName());
        tournament.setLocation(createTournamentRequest.getLocation());
        tournament.setStartDate(createTournamentRequest.getStartDate());
        tournament.setEndDate(createTournamentRequest.getEndDate());
        tournament.setOrganizerCid(createTournamentRequest.getOrganizerCid());
        tournament.setTournamentType(TournamentType.valueOf(createTournamentRequest.getTournamentType().toUpperCase()));
        tournament.setStatus(TournamentStatus.UPCOMING);
        if(createTournamentRequest.getOrganizerCid() == null){
            throw new BadRequestException("OrganizerCid is null");
        }
        else {
            return tournamentRepository.save(tournament);
        }


    }

    public List<Tournament> getAll()
    {
        return tournamentRepository.findAll();
    }
    // GET BY ID
    public Tournament getById(Long id)
    {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
    }
    // REGISTER TEAM
    public Tournament registerTeam(Long tournamentId, String teamId) {
        Tournament t = getById(tournamentId);
        if(t.getStatus() != TournamentStatus.REGISTRATION_OPENED)
        {
            throw new BadRequestException("Tournament Registration Closed");
        }
        if(t.getRegisteredTeams().contains(teamId))
        {
            throw new BadRequestException("Team already registered");
        }
        Long id = Long.valueOf(teamId);
        if(!tournamentInterface.teamExist(id))
        {
            throw new BadRequestException("Team does not exist");
        }
        
        t.getRegisteredTeams().add(teamId);
        return tournamentRepository.save(t);
    }

    // START TOURNAMENT
    public Tournament startTournament(Long id) {
        Tournament t = tournamentRepository.getReferenceById(id);
        t.setStatus(TournamentStatus.REGISTRATION_OPENED);
        return tournamentRepository.save(t);
    }
    // SET TOURNAMENT TYPE 3, 5 STAR, BF, INDIVIDUAL
    public Tournament setTournamentType(Long id, int tournamentType) {
        Tournament t = getById(id);
        switch (tournamentType) {
            case 1:
                t.setTournamentType(TournamentType.BF1);
                break;
                case 2:
                    t.setTournamentType(TournamentType.BF2);
                    break;
                    case 3:
                    t.setTournamentType(TournamentType.BF3);
                    break;
                    case 4:
                    t.setTournamentType(TournamentType.BF4);
                    break;
                    case 5:
                    t.setTournamentType(TournamentType.BF5);
                    break;
                    case 6:
                    t.setTournamentType(TournamentType.BF6);
                    break;
                    case 7:
                    t.setTournamentType(TournamentType.BF7);
                    break;
                    case 8:
                    t.setTournamentType(TournamentType.BF8);
                    break;
                    case 9:
                    t.setTournamentType(TournamentType.BF9);
                    break;
                    case 10:
                    t.setTournamentType(TournamentType.BF10);
                    break;
                    case 11:
                    t.setTournamentType(TournamentType.BF11);
                    break;
                    case 12:
                        t.setTournamentType(TournamentType.BF12);
                        break;
        }
        return tournamentRepository.save(t);
    }

    // COMPLETE TOURNAMENT
    public Tournament completeTournament(Long id) {
        Tournament t = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        t.setStatus(TournamentStatus.FINISHED);
        return tournamentRepository.save(t);
    }

    // OBTAIN TEAM SPECIFICATION FOR THE TOURNAMENT - ARCHERS AND SEEDED ARCHER NUMBER

    public TournamentTeamSpec getTournamentTeamSpec(Long id) {
        Tournament t = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        TournamentTeamSpec tournamentTeamSpec = new TournamentTeamSpec();
        tournamentTeamSpec.setTournamentId(t.getId());
        tournamentTeamSpec.setArcherNumber(t.getTournamentType().getArcherNumber());
        tournamentTeamSpec.setSeedNumber(t.getSeededArcher());
        return tournamentTeamSpec;
    }

}
