package gg.om.omgg.api.riot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MatchListDTO {
    private int startIndex;
    private int totalGames;
    private int endIndex;
    private List<MatchReferenceDTO> matches;
}
