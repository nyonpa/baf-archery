package com.archery.team_service.feign;

import com.archery.team_service.dto.TournamentTeamSpec;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("TOURNAMENT")
public interface TournamentInterface {

    @GetMapping("/teamSpec/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CAPTAIN')")
    public TournamentTeamSpec getTeamSpec(@PathVariable Long id);
}
