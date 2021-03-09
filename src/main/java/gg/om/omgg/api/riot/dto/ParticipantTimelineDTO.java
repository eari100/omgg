package gg.om.omgg.api.riot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class ParticipantTimelineDTO {
    private int participantId;
    private Map<String, Double> csDiffPerMinDeltas;
    private Map<String, Double> damageTakenPerMinDeltas;
    private String role;
    private Map<String, Double> damageTakenDiffPerMinDeltas;
    private Map<String, Double> xpPerMinDeltas;
    private Map<String, Double> xpDiffPerMinDeltas;
    private String lane;
    private Map<String, Double> creepsPerMinDeltas;
    private Map<String, Double> goldPerMinDeltas;
}
