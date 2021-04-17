package gg.om.omgg.domain.participant;

import gg.om.omgg.domain.match.Match;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
public class ParticipantId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Match match;
    private int participantId;

    @Builder
    public ParticipantId(Match match, int participantId) {
        this.match = match;
        this.participantId = participantId;
    }
}
