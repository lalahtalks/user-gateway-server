package io.lalahtalks.user.gateway.server.domain.account;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AccountService {

    private final AccountGateway accountGateway;

    public AccountService(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    public Mono<Account> get(AccountId accountId) {
        return accountGateway.get(accountId);
    }

    public Mono<AccountCreated> create(AccountCreationRequest request) {
        return accountGateway.create(request);
    }

}
