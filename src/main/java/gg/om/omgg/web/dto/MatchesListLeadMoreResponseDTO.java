package gg.om.omgg.web.dto;

import gg.om.omgg.domain.match.Match;
import gg.om.omgg.domain.summoner.Summoner;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MatchesListLeadMoreResponseDTO {
    private List<Match> matches;

    public MatchesListLeadMoreResponseDTO(Summoner summoner) {
        if(summoner!=null) {
            List<Match> matchList = new ArrayList<>(summoner.getMatches());
            matchList.sort((m1, m2) -> new Match().compare(m1, m2));
            this.matches = matchList;
        }
    }
}
