package org.ajou.realcoding.positioncrawler.positioncrawler.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class LeaguePosition {
    private String queueType;
    private String summonerName;
    private boolean hotStreak;
    private MiniSeriesDTO miniSeries;
    private int wins;
    private boolean veteran;
    private int losses;
    private String rank;
    private String leagueId;
    private boolean inactive;
    private boolean freshBlood;
    private String tier;
    private String summonerId;
    private int leaguePoints;
    @Id
    private String id;

    @Data
    public static class MiniSeriesDTO {
        private String progress;
        private int losses;
        private int target;
        private int wins;
    }
}
