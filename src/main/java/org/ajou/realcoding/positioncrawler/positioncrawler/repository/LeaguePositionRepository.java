package org.ajou.realcoding.positioncrawler.positioncrawler.repository;

import org.ajou.realcoding.positioncrawler.positioncrawler.domain.LeaguePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeaguePositionRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public void insertLeaguePosition(List<LeaguePosition> leaguePosition) {
        mongoTemplate.insert(leaguePosition, LeaguePosition.class);
    }
}
