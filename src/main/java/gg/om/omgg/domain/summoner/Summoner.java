package gg.om.omgg.domain.summoner;

import gg.om.omgg.domain.match.Match;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Summoner implements Serializable {
    @Id@Column(length=56)
    private String id;
    @Column(name="account_id", length=56)
    private String accountId;
    private int profileIconId;
    private long revisionDate;
    private String name;
    @Column(length=78)
    private String puuid;
    private long summonerLevel;

    @OneToMany
    @JoinTable(name="summoner_match",
            joinColumns = @JoinColumn(name="account_id"),
            inverseJoinColumns = @JoinColumn(name="game_id")
    )
    private List<Match> matches = new ArrayList<>();

    @Builder
    public Summoner(String id, String accountId, int profileIconId, long revisionDate, String name, String puuid, long summonerLevel) {
        this.id = id;
        this.accountId = accountId;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.name = name;
        this.puuid = puuid;
        this.summonerLevel = summonerLevel;
    }

    public void update(String accountId, int profileIconId, long revisionDate, String name, String puuid, long summonerLevel) {
        this.accountId = accountId;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.name = name;
        this.puuid = puuid;
        this.summonerLevel = summonerLevel;
    }
}