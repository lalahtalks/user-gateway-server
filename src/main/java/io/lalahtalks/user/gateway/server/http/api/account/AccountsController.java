package io.lalahtalks.user.gateway.server.http.api.account;

import io.lalahtalks.user.gateway.client.dto.AccountCreatedDto;
import io.lalahtalks.user.gateway.client.dto.AccountCreationRequestDto;
import io.lalahtalks.user.gateway.server.domain.account.AccountAlreadyExistsException;
import io.lalahtalks.user.gateway.server.domain.account.AccountCreated;
import io.lalahtalks.user.gateway.server.domain.account.AccountCreationRequest;
import io.lalahtalks.user.gateway.server.domain.account.AccountService;
import io.lalahtalks.user.gateway.server.domain.user.Email;
import io.lalahtalks.user.gateway.server.domain.user.Password;
import io.lalahtalks.user.gateway.server.domain.user.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;

import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayHttpPaths.ACCOUNTS_PATH;
import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayProblemType.ACCOUNT_ALREADY_EXISTS;

@RestController
@RequestMapping(ACCOUNTS_PATH)
@RequiredArgsConstructor
public class AccountsController {

    private final AccountService accountService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    AccountCreatedDto create(@RequestBody AccountCreationRequestDto requestDto) {
        var request = fromDto(requestDto);
        var created = accountService.create(request);
        return toDto(created);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    Problem handleAccountAlreadyExistsException() {
        return ACCOUNT_ALREADY_EXISTS.toProblem();
    }

    private AccountCreatedDto toDto(AccountCreated created) {
        return AccountCreatedDto.builder()
                .accountId(created.getAccountId().getValue())
                .createdAt(created.getCreatedAt())
                .build();
    }

    private AccountCreationRequest fromDto(AccountCreationRequestDto requestDto) {
        return AccountCreationRequest.builder()
                .email(new Email(requestDto.getEmail()))
                .username(new Username(requestDto.getUsername()))
                .password(new Password(requestDto.getPassword()))
                .build();
    }

}
