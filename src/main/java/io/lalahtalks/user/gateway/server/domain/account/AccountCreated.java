package io.lalahtalks.user.gateway.server.domain.account;

import java.time.Instant;

public record AccountCreated(AccountId accountId, Instant createdAt) {

}
