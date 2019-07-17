package org.ajou.realcoding.positioncrawler.positioncrawler.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.positioncrawler.positioncrawler.domain.LeaguePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class LeaguePositionRepository {
    @Autowired
    private
    MongoTemplate mongoTemplate;

    public void insertOrUpdateLeaguePosition(List<LeaguePosition> leaguePosition) {
        for (LeaguePosition position : leaguePosition) {
            mongoTemplate.save(position);
            log.info("insert or update: " + position);
        }
    }

    public List<LeaguePosition> findLeaguePosition(String encryptedSummonerId) {
        return mongoTemplate.find(Query.query(Criteria.where("summonerId").is(encryptedSummonerId)), LeaguePosition.class);
    }
}
