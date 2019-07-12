package org.ajou.realcoding.positioncrawler.positioncrawler.repository;

import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.positioncrawler.positioncrawler.domain.LeaguePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class LeaguePositionRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public boolean isExistLeaguePosition(List<LeaguePosition> leaguePosition) {
        return mongoTemplate.exists(Query.query(Criteria.where("summonerId").is(leaguePosition.get(0).getSummonerId())), LeaguePosition.class);
    }

    public void updateLeaguePosition(List<LeaguePosition> leaguePosition) {
        for (int i = 0; i < leaguePosition.size(); i++) {
            Update update = new Update();
            update.set("hotStreak", leaguePosition.get(i).isHotStreak());
            update.set("wins", leaguePosition.get(i).getWins());
            update.set("veteran", leaguePosition.get(i).isVeteran());
            update.set("losses", leaguePosition.get(i).getLosses());
            update.set("rank", leaguePosition.get(i).getRank());
            update.set("leagueId", leaguePosition.get(i).getLeagueId());
            update.set("inactive", leaguePosition.get(i).isInactive());
            update.set("freshBlood", leaguePosition.get(i).isFreshBlood());
            update.set("tier", leaguePosition.get(i).getTier());
            update.set("leaguePoints", leaguePosition.get(i).getLeaguePoints());

            UpdateResult result = mongoTemplate.updateFirst(Query.query(Criteria.where("summonerId").is(leaguePosition.get(i).getSummonerId()).and("queueType").is(leaguePosition.get(i).getQueueType())), update, LeaguePosition.class);
            log.info("update " + i + ": " + result);
        }
    }

    public void insertLeaguePosition(List<LeaguePosition> leaguePosition) {
        mongoTemplate.insert(leaguePosition, LeaguePosition.class);
    }

    public List<LeaguePosition> findLeaguePosition(String encryptedSummonerId) {
        return mongoTemplate.find(Query.query(Criteria.where("summonerId").is(encryptedSummonerId)), LeaguePosition.class);
    }
}
