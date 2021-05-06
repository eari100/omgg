package gg.om.omgg.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MatchesListLeadMoreRequestDTO {
    private String accountId;
    private String name;
    private int strIndex;
    private int endIndex;
}
