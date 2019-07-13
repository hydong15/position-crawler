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
    private final String appid = "RGAPI-cfc204d1-f12a-4497-a7ee-6bcdb3ab9896";
    private final String developerRiotgamesSummonerUrl = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={appid}";
    private final String developerRiotgamesLeagueUrl = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key={appid}";

    @Autowired
    RestTemplate restTemplate;

    public EncryptedSummonerId requestEncryptedSummonerId (String summonerName) {
        return restTemplate.exchange(developerRiotgamesSummonerUrl, HttpMethod.GET, null, EncryptedSummonerId.class, summonerName, appid).getBody();
    }

    public List<LeaguePosition> requestLeaguePosition (String encryptedSummonerId) {
        return restTemplate.exchange(developerRiotgamesLeagueUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<LeaguePosition>>() {}, encryptedSummonerId, appid).getBody();
    }
}
