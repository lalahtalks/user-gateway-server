package io.lalahtalks.user.gateway.server.domain.secret;

import java.time.Instant;

public record SecretCreated(
        SecretId secretId,
        Instant createdAt) {

}
