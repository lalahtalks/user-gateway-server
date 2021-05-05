package io.lalahtalks.user.gateway.server.domain.account;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class AccountCreated {

    @NonNull AccountId accountId;
    @NonNull Instant createdAt;

}
