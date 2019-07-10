package org.ajou.realcoding.positioncrawler.positioncrawler.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeveloperRiotgamesApiClient {
    private final String appid = "RGAPI-0b63d3e8-55ad-467f-a1a8-2fbcd592bb87";
    private final String developerRiotgamesSummonerUrl = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={appid}";
    private final String developerRiotgamesLeagueUrl = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key={appid}";

    @Autowired
    RestTemplate restTemplate;
}
