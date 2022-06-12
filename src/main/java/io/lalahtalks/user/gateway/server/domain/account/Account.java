package io.lalahtalks.user.gateway.server.domain.account;

import io.lalahtalks.user.gateway.server.domain.user.Email;
import io.lalahtalks.user.gateway.server.domain.user.Username;

import java.time.Instant;

public record Account(
        AccountId id,
        Email email,
        Username username,
        Instant createdAt) {

}
