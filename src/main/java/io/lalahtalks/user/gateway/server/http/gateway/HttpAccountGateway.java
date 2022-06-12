package io.lalahtalks.user.gateway.server.http.gateway;

import io.lalahtalks.accounts.client.dto.AccountCreatedDto;
import io.lalahtalks.accounts.client.dto.AccountCreationRequestDto;
import io.lalahtalks.accounts.client.dto.AccountDto;
import io.lalahtalks.accounts.client.http.AccountsHttpClient;
import io.lalahtalks.accounts.client.http.contract.problem.AccountAlreadyExistsProblem;
import io.lalahtalks.accounts.client.http.contract.problem.AccountNotFoundProblem;
import io.lalahtalks.user.gateway.server.domain.account.*;
import io.lalahtalks.user.gateway.server.domain.user.Email;
import io.lalahtalks.user.gateway.server.domain.user.Username;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class HttpAccountGateway implements AccountGateway {

    private final AccountsHttpClient accountsHttpClient;

    public HttpAccountGateway(AccountsHttpClient accountsHttpClient) {
        this.accountsHttpClient = accountsHttpClient;
    }

    @Override
    public Mono<Account> get(AccountId accountId) {
        return accountsHttpClient.get(accountId.value())
                .map(this::from)
                .doOnError(AccountNotFoundProblem.class, e -> {
                    throw new AccountNotFoundException();
                });
    }

    @Override
    public Mono<AccountCreated> create(AccountCreationRequest request) {
        var requestDto = to(request);
        return accountsHttpClient.create(requestDto)
                .map(this::from)
                .doOnError(AccountAlreadyExistsProblem.class, e -> {
                    throw new AccountAlreadyExistsException();
                });
    }

    private Account from(AccountDto accountDto) {
        return new Account(
                new AccountId(accountDto.id()),
                new Email(accountDto.email()),
                new Username(accountDto.username()),
                accountDto.createdAt());
    }

    private AccountCreated from(AccountCreatedDto accountCreatedDto) {
        return new AccountCreated(
                new AccountId(accountCreatedDto.accountId()),
                accountCreatedDto.createdAt());
    }

    private AccountCreationRequestDto to(AccountCreationRequest request) {
        return new AccountCreationRequestDto(
                request.email().value(),
                request.username().value(),
                request.password().value());
    }

}
