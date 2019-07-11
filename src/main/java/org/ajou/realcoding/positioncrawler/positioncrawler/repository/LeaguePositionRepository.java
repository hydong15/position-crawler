package org.ajou.realcoding.positioncrawler.positioncrawler.repository;

import org.ajou.realcoding.positioncrawler.positioncrawler.domain.LeaguePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeaguePositionRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public boolean isExistLeaguePosition(List<LeaguePosition> leaguePosition) {
        if (mongoTemplate.exists(Query.query(Criteria.where("summonerId").is(leaguePosition.get(0).getSummonerId())), LeaguePosition.class)) {
            return true;
        }
        else {
            return false;
        }
    }

    //WIP
    public void updateLeaguePosition(List<LeaguePosition> leaguePosition) {
        //mongoTemplate.update()
    }

    public void insertLeaguePosition(List<LeaguePosition> leaguePosition) {
        mongoTemplate.insert(leaguePosition, LeaguePosition.class);
    }

    public List<LeaguePosition> findLeaguePosition(String encryptedSummonerId) {
        List<LeaguePosition> leaguePositionList = mongoTemplate.find(Query.query(Criteria.where("summonerId").is(encryptedSummonerId)), LeaguePosition.class);
        return leaguePositionList;
    }
}
