package io.lalahtalks.user.gateway.server.domain.secret;

import java.time.Instant;

public record Secret(
        SecretId id,
        SecretEncoded encoded,
        Instant createdAt) {

}
