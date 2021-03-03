package gg.om.omgg.domain.match;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatchReferenceCustomRepositoryImpl implements MatchReferenceCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public long findMaxGameIdByAccountId(String accountId) {
        return queryFactory
                .select(QMatchReference.matchReference.id.gameId.max())
                .from(QMatchReference.matchReference)
                .where(QMatchReference.matchReference.id.summoner.accountId.eq(accountId))
                .fetchOne();
    }
}
