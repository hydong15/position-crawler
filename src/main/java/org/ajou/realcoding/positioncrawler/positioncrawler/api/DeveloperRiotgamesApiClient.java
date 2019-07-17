package org.ajou.realcoding.positioncrawler.positioncrawler.api;

import org.ajou.realcoding.positioncrawler.positioncrawler.domain.EncryptedSummonerId;
import org.ajou.realcoding.positioncrawler.positioncrawler.domain.LeaguePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DeveloperRiotgamesApiClient {
    private final String appid = "RGAPI-4de1631b-8468-4ab6-b4ab-b48c7a54c534";
    private final String developerRiotgamesSummonerUri = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={appid}";
    private final String developerRiotgamesLeagueUri = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key={appid}";

    @Autowired
    private
    RestTemplate restTemplate;

    public EncryptedSummonerId requestEncryptedSummonerId (String summonerName) {
        return restTemplate.exchange(developerRiotgamesSummonerUri, HttpMethod.GET, null, EncryptedSummonerId.class, summonerName, appid).getBody();
    }

    public List<LeaguePosition> requestLeaguePosition (String encryptedSummonerId) {
        return restTemplate.exchange(developerRiotgamesLeagueUri, HttpMethod.GET, null, new ParameterizedTypeReference<List<LeaguePosition>>() {}, encryptedSummonerId, appid).getBody();
    }
}
