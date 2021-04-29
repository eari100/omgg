package gg.om.omgg.domain.summoner;

import com.querydsl.jpa.impl.JPAQueryFactory;
import gg.om.omgg.domain.match.QMatch;
import gg.om.omgg.domain.participant.QParticipant;
import gg.om.omgg.web.dto.MatchesListLeadMoreResponseDTO;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SummonerCustomRepositoryImpl implements SummonerCustomRepository {
    private final JPAQueryFactory queryFactory;

    QSummoner summoner = QSummoner.summoner;
    QMatch match = QMatch.match;
    QParticipant participant = QParticipant.participant;

    @Override
    public SummonerIntegrationInformationResponseDTO findSummonerIntegrationInformationByName(String summonerName) {
        Summoner result = queryFactory
                .selectFrom(summoner)
                .leftJoin(summoner.matches, match).fetchJoin()
                .leftJoin(match.participants, participant).fetchJoin()
                .where(summoner.name.eq(summonerName))
                // match.gameId 는 Set 자료구조에 담기므로 sql에서 order by 를 해도 의미가 없어집니다.
                // 따라서, SummonerIntegrationInformationResponseDTO 클래스내에 선언 하였습니다.
                // .orderBy(match.gameId.desc())
                .offset(0)
                .limit(20)
                .fetchOne();

        return new SummonerIntegrationInformationResponseDTO(result);
    }

    @Override
    public MatchesListLeadMoreResponseDTO findMatchesListLeadMoreById(String id, int endIndex) {
        Summoner result = queryFactory
                .selectFrom(summoner)
                .leftJoin(summoner.matches, match).fetchJoin()
                .leftJoin(match.participants, participant).fetchJoin()
                .where(summoner.id.eq(id))
                // match.gameId 는 Set 자료구조에 담기므로 sql에서 order by 를 해도 의미가 없어집니다.
                // 따라서, SummonerIntegrationInformationResponseDTO 클래스내에 선언 하였습니다.
                // .orderBy(match.gameId.desc())
                .offset(0)
                .limit(endIndex)
                .fetchOne();

        return new MatchesListLeadMoreResponseDTO(result);
    }
}
