package io.lalahtalks.user.gateway.server.domain.account;

import io.lalahtalks.user.gateway.server.domain.user.Email;
import io.lalahtalks.user.gateway.server.domain.user.Password;
import io.lalahtalks.user.gateway.server.domain.user.Username;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class AccountCreationRequest {

    @NonNull Email email;
    @NonNull Username username;
    @NonNull Password password;

}
