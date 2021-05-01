package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.MatchDTO;
import gg.om.omgg.api.riot.dto.MatchListDTO;
import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.domain.match.MatchRepository;
import gg.om.omgg.domain.participant.ParticipantRepository;
import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import gg.om.omgg.web.dto.IntegrationInfoResponseDTO;
import gg.om.omgg.web.dto.MatchesListLeadMoreResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
public class SummonerService {
    private final SummonerRepository summonerRepository;
    private final SummonerParser summonerParser;
    private final MatchRepository matchRepository;
    private final ParticipantRepository participantRepository;

    @Transactional
    public List<IntegrationInfoResponseDTO> findByName(String name) {

        List<IntegrationInfoResponseDTO> dtoList = findIntegrationInfoByName(name, 200);

        if(dtoList.size()==0)
            dtoList = renewData(name);

        return dtoList;
    }

    @Transactional
    public List<IntegrationInfoResponseDTO> renewData(String name) {
        Optional<SummonerDTO> summonerDTO = summonerParser.getJSONData(name);
        if(summonerDTO.isPresent()) {
            Summoner summoner = summonerDTO.get().toEntity();

            MatchListParser matchListParser = new MatchListParser();
            Optional<MatchListDTO> matchListDTO = matchListParser.getJSONData(summonerDTO.get().getAccountId(),20);

            if(matchListDTO.isPresent()) {
                HashSet<Long> gameIds = matchListDTO.get().getMatches()
                                        .stream()
                                        .map(match->match.getGameId())
                                        .collect(HashSet::new, HashSet::add, HashSet::addAll);

                for(long gameId:gameIds) {
                    MatchDetailParser matchDetailParser = new MatchDetailParser();
                    Optional<MatchDTO> matchDTO = matchDetailParser.getJSONData(gameId);

                    if(matchDTO.isPresent()) {
                        matchRepository.save(matchDTO.get().matchToEntity());
                        summoner.getMatches().add(matchDTO.get().matchToEntity());

                        participantRepository.saveAll(matchDTO.get().participantToEntity());
                    }
                }
            }
            // save()으로 등록,수정 둘다 가능합니다.
            summonerRepository.save(summoner);
        } else {
            Optional<Summoner> summoner = summonerRepository.findByName(name);
            if(summoner.isPresent()) {
                // 라이엇서버에 소환사가 존재하지 않는다면 해당 데이터의 summoner.name을 공백으로 update합니다
                summoner.get().update(summoner.get().getAccountId(), summoner.get().getProfileIconId(), summoner.get().getRevisionDate(),
                        "", summoner.get().getPuuid(), summoner.get().getSummonerLevel());
            }
        }

        return findIntegrationInfoByName(name, 200);
    }

    @Transactional
    public List<IntegrationInfoResponseDTO> matchesListLeadMore(String name, String accountId, int endIndex) {
        MatchListParser matchListParser = new MatchListParser();
        Optional<MatchListDTO> matchListDTO = matchListParser.getJSONData(accountId, endIndex);

        if(matchListDTO.isPresent()) {
            HashSet<Long> gameIds = matchListDTO.get().getMatches()
                    .stream()
                    .map(match->match.getGameId())
                    .collect(HashSet::new, HashSet::add, HashSet::addAll);

            for(long gameId:gameIds) {
                MatchDetailParser matchDetailParser = new MatchDetailParser();
                Optional<MatchDTO> matchDTO = matchDetailParser.getJSONData(gameId);

                if(matchDTO.isPresent()) {
                    matchRepository.save(matchDTO.get().matchToEntity());
                    participantRepository.saveAll(matchDTO.get().participantToEntity());
                }
            }
        }

        return findIntegrationInfoByName(name, endIndex);
    }

    private List<IntegrationInfoResponseDTO> findIntegrationInfoByName(String name, int endIndex) {
        List<Object[]> objList = summonerRepository.findIntegrationInfoByName(name, endIndex);
        List<IntegrationInfoResponseDTO> dtoList = new ArrayList<>();

        for(Object[] obj : objList) {
            dtoList.add(
                    IntegrationInfoResponseDTO.builder()
                            .profileIconId((int)obj[0])
                            .name((String)obj[1])
                            .summonerLevel(((Number)obj[2]).longValue())
                            .id((String)obj[3])

                            .gameId(((Number)obj[4]).longValue())
                            .queueId((int)obj[5])
                            .platformId((String)obj[6])
                            .seasonId((int)obj[7])
                            .gameCreation(((Number)obj[8]).longValue())
                            .gameDuration(((Number)obj[9]).longValue())

                            .championId((int)obj[10])
                            .spell1Id((int)obj[11])
                            .spell2Id((int)obj[12])
                            .item0((int)obj[13])
                            .item1((int)obj[14])
                            .item2((int)obj[15])
                            .item3((int)obj[16])
                            .item4((int)obj[17])
                            .item5((int)obj[18])
                            .item6((int)obj[19])
                            .kills((int)obj[20])
                            .deaths((int)obj[21])
                            .assists((int)obj[22])
                            .perk0((int)obj[23])
                            .perkSubStyle((int)obj[24])
                            .champLevel((int)obj[25])
                            .totalMinionsKilled((int)obj[26])
                            .neutralMinionsKilled((int)obj[27])
                            .visionWardsBoughtInGame((int)obj[28])

                            .player1ChampionId((int)obj[29])
                            .player2ChampionId((int)obj[30])
                            .player3ChampionId((int)obj[31])
                            .player4ChampionId((int)obj[32])
                            .player5ChampionId((int)obj[33])
                            .player6ChampionId((int)obj[34])
                            .player7ChampionId((int)obj[35])
                            .player8ChampionId((int)obj[36])
                            .player9ChampionId((int)obj[37])
                            .player10ChampionId((int)obj[38])
                            .player1SummonerName((String)obj[39])
                            .player2SummonerName((String)obj[40])
                            .player3SummonerName((String)obj[41])
                            .player4SummonerName((String)obj[42])
                            .player5SummonerName((String)obj[43])
                            .player6SummonerName((String)obj[44])
                            .player7SummonerName((String)obj[45])
                            .player8SummonerName((String)obj[46])
                            .player9SummonerName((String)obj[47])
                            .player10SummonerName((String)obj[48])
                            .win((boolean)obj[49])
                            .build()
            );
        }
        return dtoList;
    }
}
