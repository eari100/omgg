package gg.om.omgg.domain.participant;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class ParticipantId implements Serializable {
    @Column(name="game_id")
    private long gameId;
    private int participantId;

    @Builder
    public ParticipantId(long gameId, int participantId) {
        this.gameId = gameId;
        this.participantId = participantId;
    }
}
