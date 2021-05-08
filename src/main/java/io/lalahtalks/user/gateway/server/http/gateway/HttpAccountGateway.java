package io.lalahtalks.user.gateway.server.http.gateway;

import io.lalahtalks.accounts.client.dto.AccountCreationRequestDto;
import io.lalahtalks.accounts.client.http.AccountsHttpClient;
import io.lalahtalks.user.gateway.server.domain.account.AccountCreated;
import io.lalahtalks.user.gateway.server.domain.account.AccountCreationRequest;
import io.lalahtalks.user.gateway.server.domain.account.AccountGateway;
import io.lalahtalks.user.gateway.server.domain.account.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpAccountGateway implements AccountGateway {

    private final AccountsHttpClient accountsHttpClient;

    @Override
    public AccountCreated create(AccountCreationRequest request) {
        var requestDto = toDto(request);
        var accountCreatedDto = accountsHttpClient.create(requestDto);
        return fromDto(accountCreatedDto);
    }

    private AccountCreated fromDto(io.lalahtalks.accounts.client.dto.AccountCreatedDto accountCreatedDto) {
        return AccountCreated.builder()
                .accountId(new AccountId(accountCreatedDto.getAccountId()))
                .createdAt(accountCreatedDto.getCreatedAt())
                .build();
    }

    private AccountCreationRequestDto toDto(AccountCreationRequest request) {
        return AccountCreationRequestDto.builder()
                .email(request.getEmail().getValue())
                .password(request.getPassword().getValue())
                .build();
    }

}
