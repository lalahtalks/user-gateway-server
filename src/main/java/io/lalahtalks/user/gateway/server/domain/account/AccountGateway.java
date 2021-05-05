package io.lalahtalks.user.gateway.server.domain.account;

public interface AccountGateway {

    AccountCreated create(AccountCreationRequest request);

}
