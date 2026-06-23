package com.archery.team_service.service;


import com.archery.team_service.model.Team;
import com.archery.team_service.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private TeamRepository teamRepository;
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    public Team createTeam(String teamName, String cid) {
        Team team = new Team();
        team.setTeamName(teamName);
        team.setCaptainCid(cid);
        team.getMembers().add(cid);

        return teamRepository.save(team);
    }

    public Team joinTeam(String cid, Long teamId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        if (team.getMembers().size() >= team.getMaxMembers()) {
            throw new RuntimeException("Team is full");
        }

        team.getMembers().add(cid);

        return teamRepository.save(team);
    }

    public Team getTeam(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));
    }
}
