package gg.om.omgg.domain.summoner;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

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