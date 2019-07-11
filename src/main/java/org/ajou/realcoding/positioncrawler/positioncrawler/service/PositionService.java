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
        String encryptedSummonerId = getEncryptedSummonerId(summonerName);
        List<LeaguePosition> leaguePositionList = developerRiotgamesApiClient.requestLeaguePosition(encryptedSummonerId);

        if (leaguePositionRepository.isExistLeaguePosition(leaguePositionList)) {
            leaguePositionRepository.updateLeaguePosition(leaguePositionList);
            log.info("League position has been updated successfully. ()", leaguePositionList);
        }
        else {
            leaguePositionRepository.insertLeaguePosition(leaguePositionList);
            log.info("League position has been inserted successfully. ()", leaguePositionList);
        }

        return leaguePositionRepository.findLeaguePosition(encryptedSummonerId);
    }
}
