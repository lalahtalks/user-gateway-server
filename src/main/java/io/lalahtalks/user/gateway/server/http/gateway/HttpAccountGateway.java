package io.lalahtalks.user.gateway.server.http.gateway;

import io.lalahtalks.accounts.client.dto.AccountCreatedDto;
import io.lalahtalks.accounts.client.dto.AccountCreationRequestDto;
import io.lalahtalks.accounts.client.dto.AccountDto;
import io.lalahtalks.accounts.client.http.AccountsHttpClient;
import io.lalahtalks.user.gateway.server.domain.account.*;
import io.lalahtalks.user.gateway.server.domain.user.Email;
import io.lalahtalks.user.gateway.server.domain.user.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpAccountGateway implements AccountGateway {

    private final AccountsHttpClient accountsHttpClient;

    @Override
    public Account get(AccountId accountId) {
        try {
            var accountDto = accountsHttpClient.get(accountId.getValue());
            return fromDto(accountDto);
        } catch (io.lalahtalks.accounts.client.http.exception.AccountNotFoundException e) {
            throw new AccountNotFoundException();
        }
    }

    @Override
    public AccountCreated create(AccountCreationRequest request) {
        try {
            var requestDto = toDto(request);
            var accountCreatedDto = accountsHttpClient.create(requestDto);
            return fromDto(accountCreatedDto);
        } catch (io.lalahtalks.accounts.client.http.exception.AccountAlreadyExistsException e) {
            throw new AccountAlreadyExistsException();
        }
    }

    private Account fromDto(AccountDto accountDto) {
        return Account.builder()
                .id(new AccountId(accountDto.getId()))
                .email(new Email(accountDto.getEmail()))
                .username(new Username(accountDto.getUsername()))
                .createdAt(accountDto.getCreatedAt())
                .build();
    }

    private AccountCreated fromDto(AccountCreatedDto accountCreatedDto) {
        return AccountCreated.builder()
                .accountId(new AccountId(accountCreatedDto.getAccountId()))
                .createdAt(accountCreatedDto.getCreatedAt())
                .build();
    }

    private AccountCreationRequestDto toDto(AccountCreationRequest request) {
        return AccountCreationRequestDto.builder()
                .email(request.getEmail().getValue())
                .username(request.getUsername().getValue())
                .password(request.getPassword().getValue())
                .build();
    }

}
