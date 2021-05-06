package gg.om.omgg.web.dto;

import gg.om.omgg.domain.match.Match;
import gg.om.omgg.domain.summoner.Summoner;
import lombok.Getter;

import java.util.*;

@Getter
public class SummonerIntegrationInformationResponseDTO {
    private int profileIconId;
    private String name;
    private long summonerLevel;
    private String id;
    private List<Match> matches;

    public SummonerIntegrationInformationResponseDTO(Summoner summoner) {
        if(summoner!=null) {
            this.profileIconId = summoner.getProfileIconId();
            this.name = summoner.getName();
            this.summonerLevel = summoner.getSummonerLevel();
            this.id = summoner.getId();

            List<Match> matchList = new ArrayList<>(summoner.getMatches());
            matchList.sort((m1,m2)->new Match().compare(m1,m2));
            this.matches = matchList;
        }
    }
}
