package gg.om.omgg.domain.match;

public interface MatchReferenceCustomRepository {
    long findMaxGameIdByAccountId(String accountId);
}
