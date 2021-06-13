package io.lalahtalks.user.gateway.server.http.api.account;

import io.lalahtalks.user.gateway.client.dto.AccountCreatedDto;
import io.lalahtalks.user.gateway.client.dto.AccountCreationRequestDto;
import io.lalahtalks.user.gateway.client.dto.AccountDto;
import io.lalahtalks.user.gateway.server.domain.account.AccountAlreadyExistsException;
import io.lalahtalks.user.gateway.server.domain.account.AccountId;
import io.lalahtalks.user.gateway.server.domain.account.AccountNotFoundException;
import io.lalahtalks.user.gateway.server.domain.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;

import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayHttpPaths.MY_ACCOUNT_PATH;
import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayProblemType.ACCOUNT_ALREADY_EXISTS;
import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayProblemType.ACCOUNT_NOT_FOUND;

@RestController
@RequestMapping(MY_ACCOUNT_PATH)
@RequiredArgsConstructor
public class MyAccountController {

    private final AccountDtoMapper accountDtoMapper;
    private final AccountService accountService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    AccountDto get(@AuthenticationPrincipal JwtAuthenticationToken authToken) {
        var accountId = new AccountId(authToken.getName());
        var account = accountService.get(accountId);
        return accountDtoMapper.toDto(account);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    AccountCreatedDto create(@RequestBody AccountCreationRequestDto requestDto) {
        var request = accountDtoMapper.fromDto(requestDto);
        var created = accountService.create(request);
        return accountDtoMapper.toDto(created);
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

}
