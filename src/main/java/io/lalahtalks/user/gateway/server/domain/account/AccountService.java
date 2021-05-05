package io.lalahtalks.user.gateway.server.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountService {

    private final AccountGateway accountGateway;

    public AccountCreated create(AccountCreationRequest request) {
        return accountGateway.create(request);
    }

}
