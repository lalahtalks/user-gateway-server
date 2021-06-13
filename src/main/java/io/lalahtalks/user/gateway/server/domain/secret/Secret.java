package io.lalahtalks.user.gateway.server.domain.secret;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class Secret {

    @NonNull SecretId id;
    @NonNull SecretName name;
    @NonNull SecretEncoded encoded;
    @NonNull Instant createdAt;

}
