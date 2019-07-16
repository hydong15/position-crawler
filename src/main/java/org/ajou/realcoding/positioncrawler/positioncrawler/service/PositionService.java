package org.ajou.realcoding.positioncrawler.positioncrawler.service;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.positioncrawler.positioncrawler.api.DeveloperRiotgamesApiClient;
import org.ajou.realcoding.positioncrawler.positioncrawler.domain.EncryptedSummonerId;
import org.ajou.realcoding.positioncrawler.positioncrawler.domain.LeaguePosition;
import org.ajou.realcoding.positioncrawler.positioncrawler.repository.LeaguePositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PositionService {
    @Autowired
    DeveloperRiotgamesApiClient developerRiotgamesApiClient;
    @Autowired
    LeaguePositionRepository leaguePositionRepository;

    public String getEncryptedSummonerId(String summonerName) {
        EncryptedSummonerId encryptedSummonerId = developerRiotgamesApiClient.requestEncryptedSummonerId(summonerName);
        return encryptedSummonerId.getId();
    }

    public List<LeaguePosition> getLeaguePositionBySummonerName(String summonerName) {
        String encryptedSummonerId = this.getEncryptedSummonerId(summonerName);
        List<LeaguePosition> leaguePositionList = developerRiotgamesApiClient.requestLeaguePosition(encryptedSummonerId);
        this.setIdBySummonerNameAndQueueType(leaguePositionList);
        leaguePositionRepository.insertOrUpdateLeaguePosition(leaguePositionList);
        return leaguePositionRepository.findLeaguePosition(encryptedSummonerId);
    }

    public void setIdBySummonerNameAndQueueType(List<LeaguePosition> leaguePositionList) {
        for (LeaguePosition position : leaguePositionList) {
            position.setId(position.getSummonerName() + position.getQueueType());
        }

        log.info("leaguePositionList: " + leaguePositionList);
    }
}
