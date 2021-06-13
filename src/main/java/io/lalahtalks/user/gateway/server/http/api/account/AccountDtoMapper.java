package io.lalahtalks.user.gateway.server.http.api.account;

import io.lalahtalks.user.gateway.client.dto.AccountCreatedDto;
import io.lalahtalks.user.gateway.client.dto.AccountCreationRequestDto;
import io.lalahtalks.user.gateway.client.dto.AccountDto;
import io.lalahtalks.user.gateway.server.domain.account.Account;
import io.lalahtalks.user.gateway.server.domain.account.AccountCreated;
import io.lalahtalks.user.gateway.server.domain.account.AccountCreationRequest;
import io.lalahtalks.user.gateway.server.domain.user.Email;
import io.lalahtalks.user.gateway.server.domain.user.Password;
import io.lalahtalks.user.gateway.server.domain.user.Username;
import org.springframework.stereotype.Component;

@Component
class AccountDtoMapper {

    AccountDto toDto(Account account) {
        return AccountDto.builder()
                .id(account.getId().getValue())
                .email(account.getEmail().getValue())
                .username(account.getUsername().getValue())
                .createdAt(account.getCreatedAt())
                .build();
    }

    AccountCreatedDto toDto(AccountCreated created) {
        return AccountCreatedDto.builder()
                .accountId(created.getAccountId().getValue())
                .createdAt(created.getCreatedAt())
                .build();
    }

    AccountCreationRequest fromDto(AccountCreationRequestDto requestDto) {
        return AccountCreationRequest.builder()
                .email(new Email(requestDto.getEmail()))
                .username(new Username(requestDto.getUsername()))
                .password(new Password(requestDto.getPassword()))
                .build();
    }

}
