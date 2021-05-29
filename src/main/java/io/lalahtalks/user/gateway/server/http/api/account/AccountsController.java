package io.lalahtalks.user.gateway.server.http.api.account;

import io.lalahtalks.user.gateway.client.dto.AccountCreatedDto;
import io.lalahtalks.user.gateway.client.dto.AccountCreationRequestDto;
import io.lalahtalks.user.gateway.client.dto.AccountDto;
import io.lalahtalks.user.gateway.server.domain.account.*;
import io.lalahtalks.user.gateway.server.domain.user.Email;
import io.lalahtalks.user.gateway.server.domain.user.Password;
import io.lalahtalks.user.gateway.server.domain.user.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;

import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayHttpPaths.ACCOUNTS_PATH;
import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayHttpPaths.MINE_PATH;
import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayProblemType.ACCOUNT_ALREADY_EXISTS;
import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayProblemType.ACCOUNT_NOT_FOUND;

@RestController
@RequestMapping(ACCOUNTS_PATH)
@RequiredArgsConstructor
public class AccountsController {

    private final AccountService accountService;

    @GetMapping(path = MINE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    AccountDto getMine(@AuthenticationPrincipal JwtAuthenticationToken authToken) {
        var accountId = new AccountId(authToken.getName());
        var account = accountService.get(accountId);
        return toDto(account);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    AccountCreatedDto create(@RequestBody AccountCreationRequestDto requestDto) {
        var request = fromDto(requestDto);
        var created = accountService.create(request);
        return toDto(created);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    Problem handleAccountNotFoundException() {
        return ACCOUNT_NOT_FOUND.toProblem();
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    Problem handleAccountAlreadyExistsException() {
        return ACCOUNT_ALREADY_EXISTS.toProblem();
    }

    private AccountDto toDto(Account account) {
        return AccountDto.builder()
                .id(account.getId().getValue())
                .email(account.getEmail().getValue())
                .username(account.getUsername().getValue())
                .createdAt(account.getCreatedAt())
                .build();
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
