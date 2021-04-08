package gg.om.omgg.domain.summoner;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gg.om.omgg.domain.match.QMatch;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SummonerCustomRepositoryImpl implements SummonerCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<SummonerIntegrationInformationResponseDTO> findSummonerIntegrationInformationByName(String summonerName) {
        QSummoner summoner = QSummoner.summoner;
        QMatch match = QMatch.match;

        List<Tuple> matchsInfo = queryFactory
                .select(summoner, match)
                .from(summoner)
                .innerJoin(summoner.matches, match)
                .where(summoner.name.eq(summonerName))
                .offset(0)
                .limit(20)
                .fetch();

        return matchsInfo.stream()
                .map(m -> new SummonerIntegrationInformationResponseDTO(m.get(summoner), m.get(match)))
                .collect(Collectors.toList());
    }
}
