package io.lalahtalks.user.gateway.server.domain.account;

import reactor.core.publisher.Mono;

public interface AccountGateway {

    Mono<Account> get(AccountId accountId);

    Mono<AccountCreated> create(AccountCreationRequest request);

}
