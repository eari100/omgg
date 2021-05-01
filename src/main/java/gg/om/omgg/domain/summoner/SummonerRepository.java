package gg.om.omgg.domain.summoner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SummonerRepository extends JpaRepository<Summoner, String>, SummonerCustomRepository {
    Optional<Summoner> findByName(String summonerName);

    @Query(value="select " +
            "x.profile_icon_id, x.s_name, x.summoner_level, x.s_id, " +
            "x.m_game_id, x.queue_id, x.platform_id, x.season_id, x.game_creation, x.game_duration, " +
            "mp.champion_id, mp.spell1id, mp.spell2id, mp.item0, mp.item1, " +
            "mp.item2, mp.item3, mp.item4, mp.item5, mp.item6, " +
            "mp.kills, mp.deaths, mp.assists, mp.perk0, mp.perk_sub_style, " +
            "mp.champ_level, mp.total_minions_killed, mp.neutral_minions_killed, mp.vision_wards_bought_in_game, " +
            "max(case when x.participant_id = 1 then x.champion_id end), " +
            "max(case when x.participant_id = 2 then x.champion_id end), " +
            "max(case when x.participant_id = 3 then x.champion_id end), " +
            "max(case when x.participant_id = 4 then x.champion_id end), " +
            "max(case when x.participant_id = 5 then x.champion_id end), " +
            "max(case when x.participant_id = 6 then x.champion_id end), " +
            "max(case when x.participant_id = 7 then x.champion_id end), " +
            "max(case when x.participant_id = 8 then x.champion_id end), " +
            "max(case when x.participant_id = 9 then x.champion_id end), " +
            "max(case when x.participant_id = 10 then x.champion_id end), " +
            "max(case when x.participant_id = 1 then x.p_summoner_name end), " +
            "max(case when x.participant_id = 2 then x.p_summoner_name end), " +
            "max(case when x.participant_id = 3 then x.p_summoner_name end), " +
            "max(case when x.participant_id = 4 then x.p_summoner_name end), " +
            "max(case when x.participant_id = 5 then x.p_summoner_name end), " +
            "max(case when x.participant_id = 6 then x.p_summoner_name end), " +
            "max(case when x.participant_id = 7 then x.p_summoner_name end), " +
            "max(case when x.participant_id = 8 then x.p_summoner_name end), " +
            "max(case when x.participant_id = 9 then x.p_summoner_name end), " +
            "max(case when x.participant_id = 10 then x.p_summoner_name end)," +
            "mp.win " +
            "from ( " +
            "select " +
            "s.profile_icon_id, s.name as s_name, s.summoner_level, s.id as s_id, " +
            "m.game_id as m_game_id, m.queue_id, m.platform_id, m.season_id, m.game_creation, m.game_duration, " +
            "p.participant_id, p.summoner_name as p_summoner_name, p.champion_id " +
            "from summoner s  " +
            "left outer join summoner_match sm " +
            "on s.account_id = sm.account_id " +
            "left outer join match m " +
            "on sm.game_id = m.game_id " +
            "left outer join participant p " +
            "on m.game_id = p.game_id " +
            "where s.name = :name " +
            "limit 0,200 " +
            ") x inner join participant mp " +
            "on x.m_game_id = mp.game_id " +
            "and mp.summoner_name = :name " +
            "group by x.m_game_id " +
            "order by x.m_game_id desc ", nativeQuery = true)
    List<Object[]> findIntegrationInfoByName(@Param("name") String summonerName);
}
