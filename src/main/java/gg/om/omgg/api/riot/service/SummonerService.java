package gg.om.omgg.api.riot.service;

import gg.om.omgg.api.riot.dto.MatchDTO;
import gg.om.omgg.api.riot.dto.MatchListDTO;
import gg.om.omgg.api.riot.dto.SummonerDTO;
import gg.om.omgg.domain.match.Match;
import gg.om.omgg.domain.match.MatchRepository;
import gg.om.omgg.domain.participant.Participant;
import gg.om.omgg.domain.participant.ParticipantRepository;
import gg.om.omgg.domain.summoner.Summoner;
import gg.om.omgg.domain.summoner.SummonerRepository;
import gg.om.omgg.web.dto.IntegrationInfoResponseDTO;
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

        List<IntegrationInfoResponseDTO> dtoList = findIntegrationInfoByName(name, 0, 200);

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
                    Optional<Match> findMatch = matchRepository.findById(gameId);
                    MatchDetailParser matchDetailParser = new MatchDetailParser();
                    Optional<MatchDTO> matchDTO = matchDetailParser.getJSONData(gameId);

                    if(findMatch.isPresent()) {
                        // 아래 작성된 summonerRepository.save로 인해 해당 코드가 없으면 데이터 삭제가 일어납니다.
                        summoner.getMatches().add(matchDTO.get().matchToEntity());

                    } else {
                        if(matchDTO.isPresent()) {
                            matchRepository.save(matchDTO.get().matchToEntity());
                            summoner.getMatches().add(matchDTO.get().matchToEntity());

                            participantRepository.saveAll(matchDTO.get().participantToEntity());
                        }
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

        return findIntegrationInfoByName(name, 0, 200);
    }

    @Transactional
    public List<IntegrationInfoResponseDTO> matchesListLeadMore(String name, String accountId, int strIndex, int endIndex) {
        MatchListParser matchListParser = new MatchListParser();
        Optional<MatchListDTO> matchListDTO = matchListParser.getJSONData(accountId, endIndex);

        if(matchListDTO.isPresent()) {
            Optional<Summoner> findSummoner = summonerRepository.findByName(name);

            HashSet<Long> gameIds = matchListDTO.get().getMatches()
                    .stream()
                    .map(match->match.getGameId())
                    .collect(HashSet::new, HashSet::add, HashSet::addAll);

            for(long gameId:gameIds) {
                Optional<Match> findMatch = matchRepository.findById(gameId);
                MatchDetailParser matchDetailParser = new MatchDetailParser();
                Optional<MatchDTO> matchDTO = matchDetailParser.getJSONData(gameId);

                if(findMatch.isPresent()) {
                    // 아래 작성된 summonerRepository.save로 인해 해당 코드가 없으면 데이터 삭제가 일어납니다.
                    findSummoner.get().getMatches().add(matchDTO.get().matchToEntity());

                } else {
                    if(matchDTO.isPresent()) {
                        matchRepository.save(matchDTO.get().matchToEntity());
                        findSummoner.get().getMatches().add(matchDTO.get().matchToEntity());

                        participantRepository.saveAll(matchDTO.get().participantToEntity());
                    }
                }
            }
        }

        return findIntegrationInfoByName(name, strIndex, endIndex);
    }

    private List<IntegrationInfoResponseDTO> findIntegrationInfoByName(String name, int strIndex, int endIndex) {
        List<Object[]> objList = summonerRepository.findIntegrationInfoByName(name, strIndex, endIndex);
        List<IntegrationInfoResponseDTO> dtoList = new ArrayList<>();

        for(Object[] obj : objList) {
            dtoList.add(
                    IntegrationInfoResponseDTO.builder()
                            .profileIconId(Integer.parseInt(String.valueOf(obj[0])))
                            .name((String)obj[1])
                            .summonerLevel(((Number)obj[2]).longValue())
                            .id((String)obj[3])

                            .gameId(((Number)obj[4]).longValue())
                            .queueId(Integer.parseInt(String.valueOf(obj[5])))
                            .platformId((String)obj[6])
                            .seasonId(Integer.parseInt(String.valueOf(obj[7])))
                            .gameCreation(((Number)obj[8]).longValue())
                            .gameDuration(((Number)obj[9]).longValue())

                            .championId(Integer.parseInt(String.valueOf(obj[10])))
                            .spell1Id(Integer.parseInt(String.valueOf(obj[11])))
                            .spell2Id(Integer.parseInt(String.valueOf(obj[12])))
                            .item0(Integer.parseInt(String.valueOf(obj[13])))
                            .item1(Integer.parseInt(String.valueOf(obj[14])))
                            .item2(Integer.parseInt(String.valueOf(obj[15])))
                            .item3(Integer.parseInt(String.valueOf(obj[16])))
                            .item4(Integer.parseInt(String.valueOf(obj[17])))
                            .item5(Integer.parseInt(String.valueOf(obj[18])))
                            .item6(Integer.parseInt(String.valueOf(obj[19])))
                            .kills(Integer.parseInt(String.valueOf(obj[20])))
                            .deaths(Integer.parseInt(String.valueOf(obj[21])))
                            .assists(Integer.parseInt(String.valueOf(obj[22])))
                            .perk0(Integer.parseInt(String.valueOf(obj[23])))
                            .perkSubStyle(Integer.parseInt(String.valueOf(obj[24])))
                            .champLevel(Integer.parseInt(String.valueOf(obj[25])))
                            .totalMinionsKilled(Integer.parseInt(String.valueOf(obj[26])))
                            .neutralMinionsKilled(Integer.parseInt(String.valueOf(obj[27])))
                            .visionWardsBoughtInGame(Integer.parseInt(String.valueOf(obj[28])))

                            .player1ChampionId(Integer.parseInt(String.valueOf(obj[29])))
                            .player2ChampionId(Integer.parseInt(String.valueOf(obj[30])))
                            .player3ChampionId(Integer.parseInt(String.valueOf(obj[31])))
                            .player4ChampionId(Integer.parseInt(String.valueOf(obj[32])))
                            .player5ChampionId(Integer.parseInt(String.valueOf(obj[33])))
                            .player6ChampionId(Integer.parseInt(String.valueOf(obj[34])))
                            .player7ChampionId(Integer.parseInt(String.valueOf(obj[35])))
                            .player8ChampionId(Integer.parseInt(String.valueOf(obj[36])))
                            .player9ChampionId(Integer.parseInt(String.valueOf(obj[37])))
                            .player10ChampionId(Integer.parseInt(String.valueOf(obj[38])))
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
