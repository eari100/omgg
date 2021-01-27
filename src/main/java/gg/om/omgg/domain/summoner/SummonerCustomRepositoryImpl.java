package gg.om.omgg.domain.summoner;

import com.querydsl.jpa.impl.JPAQueryFactory;
import gg.om.omgg.domain.match.Match;
import gg.om.omgg.domain.match.QMatch;
import gg.om.omgg.web.dto.SummonerResponseDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SummonerCustomRepositoryImpl implements SummonerCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<SummonerResponseDTO> findSummonerIntegrationInformation() {
        List<Match> matches = queryFactory
                .selectFrom(QMatch.match)
                .leftJoin(QMatch.match.id.summoner, QSummoner.summoner).fetchJoin()
                .fetch();

        return matches.stream()
                .map(m -> new SummonerResponseDTO(m.getId().getSummoner()))
                .collect(Collectors.toList());
    }
}
