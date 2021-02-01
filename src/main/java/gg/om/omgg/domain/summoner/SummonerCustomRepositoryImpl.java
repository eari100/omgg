package gg.om.omgg.domain.summoner;

import com.querydsl.jpa.impl.JPAQueryFactory;
import gg.om.omgg.domain.match.MatchReference;
import gg.om.omgg.domain.match.QMatchReference;
import gg.om.omgg.web.dto.SummonerIntegrationInformationResponseDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SummonerCustomRepositoryImpl implements SummonerCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<SummonerIntegrationInformationResponseDTO> findSummonerIntegrationInformationByName(String summonerName) {
        List<MatchReference> matchReferences = queryFactory
                .selectFrom(QMatchReference.matchReference)
                .rightJoin(QMatchReference.matchReference.id.summoner, QSummoner.summoner).fetchJoin()
                .where(QMatchReference.matchReference.id.summoner.name.eq(summonerName))
                .offset(0)
                .limit(20)
                .fetch();

        return matchReferences.stream()
                .map(m -> new SummonerIntegrationInformationResponseDTO(m))
                .collect(Collectors.toList());
    }
}
