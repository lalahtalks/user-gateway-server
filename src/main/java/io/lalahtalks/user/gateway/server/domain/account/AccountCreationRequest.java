package io.lalahtalks.user.gateway.server.domain.account;

import io.lalahtalks.user.gateway.server.domain.user.Email;
import io.lalahtalks.user.gateway.server.domain.user.Password;
import io.lalahtalks.user.gateway.server.domain.user.Username;

public record AccountCreationRequest(
        Email email,
        Username username,
        Password password) {

}
