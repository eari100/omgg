package gg.om.omgg.domain.summoner;

import com.querydsl.jpa.impl.JPAQueryFactory;
import gg.om.omgg.domain.match.MatchReference;
import gg.om.omgg.domain.match.QMatchReference;
import gg.om.omgg.web.dto.SummonerResponseDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SummonerCustomRepositoryImpl implements SummonerCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<SummonerResponseDTO> findSummonerIntegrationInformationByName(String summonerName) {
        List<MatchReference> matchReferences = queryFactory
                .selectFrom(QMatchReference.matchReference)
                .leftJoin(QMatchReference.matchReference.id.summoner, QSummoner.summoner).fetchJoin()
                .where(QMatchReference.matchReference.id.summoner.name.eq(summonerName))
                .fetch();

        return matchReferences.stream()
                .map(m -> new SummonerResponseDTO(m.getId().getSummoner()))
                .collect(Collectors.toList());
    }
}
