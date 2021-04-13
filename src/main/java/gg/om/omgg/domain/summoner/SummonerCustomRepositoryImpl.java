package gg.om.omgg.domain.summoner;

import com.querydsl.jpa.impl.JPAQueryFactory;
import gg.om.omgg.domain.match.QMatch;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SummonerCustomRepositoryImpl implements SummonerCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public SummonerIntegrationInformationResponseDTO findSummonerIntegrationInformationByName(String summonerName) {
        QSummoner summoner = QSummoner.summoner;
        QMatch match = QMatch.match;

        Summoner result = queryFactory
                .selectFrom(summoner)
                .leftJoin(summoner.matches, match).fetchJoin()
                .where(summoner.name.eq(summonerName))
                .offset(0)
                .limit(20)
                .fetchOne();

        return new SummonerIntegrationInformationResponseDTO(result);
    }
}
