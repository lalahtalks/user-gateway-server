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
class ApiAccountDtoMapper {

    AccountDto toDto(Account account) {
        return new AccountDto(
                account.id().value(),
                account.email().value(),
                account.username().value(),
                account.createdAt());
    }

    AccountCreatedDto toDto(AccountCreated created) {
        return new AccountCreatedDto(
                created.accountId().value(),
                created.createdAt());
    }

    AccountCreationRequest fromDto(AccountCreationRequestDto requestDto) {
        return new AccountCreationRequest(
                new Email(requestDto.email()),
                new Username(requestDto.username()),
                new Password(requestDto.password()));
    }

}
