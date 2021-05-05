package io.lalahtalks.user.gateway.server.domain.account;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class AccountCreationRequest {

    @NonNull Email email;

}
