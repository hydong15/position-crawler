package org.ajou.realcoding.positioncrawler.positioncrawler.controller;

import org.ajou.realcoding.positioncrawler.positioncrawler.domain.LeaguePosition;
import org.ajou.realcoding.positioncrawler.positioncrawler.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PositionController {
    @Autowired
    PositionService positionService;

    @GetMapping("/position-crawler/league-positions/by-summoner-name/{summonerName}")
    public List<LeaguePosition> getLeaguePosition(@PathVariable String summonerName) {
        return positionService.getLeaguePositionBySummonerName(summonerName);
    }
}
