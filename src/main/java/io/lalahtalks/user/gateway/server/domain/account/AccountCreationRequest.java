package io.lalahtalks.user.gateway.server.domain.account;

import io.lalahtalks.user.gateway.server.domain.user.Email;
import io.lalahtalks.user.gateway.server.domain.user.Password;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class AccountCreationRequest {

    @NonNull Email email;
    @NonNull Password password;

}
