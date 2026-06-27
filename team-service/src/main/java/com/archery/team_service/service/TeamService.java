package com.archery.team_service.service;


import com.archery.team_service.dto.CreateTeam;
import com.archery.team_service.dto.TournamentTeamSpec;
import com.archery.team_service.feign.TournamentInterface;
import com.archery.team_service.model.Team;
import com.archery.team_service.repository.TeamRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class TeamService {

    private final TeamRepository teamRepository;
    //  FEIGN CLIENT INTERFACE
    TournamentInterface  tournamentInterface;
    public TeamService(TeamRepository teamRepository, TournamentInterface  tournamentInterface)
    {
        this.teamRepository = teamRepository;
        this.tournamentInterface = tournamentInterface;
    }
    // CREATES TEAM
    public Team createTeam(CreateTeam request)
    {
        Team team = new Team();
        team.setTeamName(request.getTeamName());
        team.setCaptainCid(request.getCaptainCid());
        TournamentTeamSpec tournamentTeamSpec = tournamentInterface.getTeamSpec(request.getTournamentId());
        team.setMaxSeededArchers(tournamentTeamSpec.getSeedNumber());
        team.setMaxArchers(tournamentTeamSpec.getArcherNumber());
         return teamRepository.save(team);
    }
    // JOIN TEAMS
    public Team joinTeam(String cid, Long teamId)
    {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        if (team.getArcherCid().size() >= team.getMaxArchers()) {
            throw new RuntimeException("Team is full");
        }

        team.getArcherCid().add(cid);

        return teamRepository.save(team);
    }

    public Team getTeam(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));
    }
    //CHECK TEAM EXIST
    public boolean teamExists(Long teamId) {
        return teamRepository.findById(teamId).isPresent();
    }

    //ADD TEAM MEMBER
    public Team addTeamMembers(Set<String> teamMembers)
    {
        String captainCid = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Team captainTeam = teamRepository.findByCaptainCid(captainCid)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        if(captainTeam.getArcherCid().size()+teamMembers.size()> captainTeam.getMaxArchers()-1) {
            throw new RuntimeException("Team is full");
        }

        captainTeam.getArcherCid().addAll(teamMembers);
        return teamRepository.save(captainTeam);
    }
    public Set<Team> getRegisteredTeams(Set<String> teamIds)
    {
        List<Long> teamIdList = teamIds.stream()
                .map(Long::parseLong)
                .toList();
        return new HashSet<>(teamRepository.findAllById(teamIdList));
    }
}
