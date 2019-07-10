package org.ajou.realcoding.positioncrawler.positioncrawler.service;

import org.ajou.realcoding.positioncrawler.positioncrawler.api.DeveloperRiotgamesApiClient;
import org.ajou.realcoding.positioncrawler.positioncrawler.domain.EncryptedSummonerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {
    @Autowired
    DeveloperRiotgamesApiClient developerRiotgamesApiClient;

    public String getEncryptedSummonerId(String summonerName) {
        EncryptedSummonerId encryptedSummonerId = developerRiotgamesApiClient.requestEncryptedSummonerId(summonerName);
        return encryptedSummonerId.getId();
    }
}
