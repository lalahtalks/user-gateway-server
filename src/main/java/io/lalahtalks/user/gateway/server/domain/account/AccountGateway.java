package io.lalahtalks.user.gateway.server.domain.account;

public interface AccountGateway {

    Account get(AccountId accountId);

    AccountCreated create(AccountCreationRequest request);

}
