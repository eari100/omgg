package gg.om.omgg.api.riot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParticipantIdentityDTO {
    private int participantId;
    private PlayerDTO player;
}
