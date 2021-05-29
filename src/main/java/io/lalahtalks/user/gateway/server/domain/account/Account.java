package io.lalahtalks.user.gateway.server.domain.account;

import io.lalahtalks.user.gateway.server.domain.user.Email;
import io.lalahtalks.user.gateway.server.domain.user.Username;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class Account {

    @NonNull AccountId id;
    @NonNull Email email;
    @NonNull Username username;
    @NonNull Instant createdAt;

}
