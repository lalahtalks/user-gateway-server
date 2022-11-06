package io.lalahtalks.user.gateway.server.http.api.account;

import io.lalahtalks.user.gateway.client.dto.AccountCreatedDto;
import io.lalahtalks.user.gateway.client.dto.AccountCreationRequestDto;
import io.lalahtalks.user.gateway.client.dto.AccountDto;
import io.lalahtalks.user.gateway.client.http.contract.problem.AccountAlreadyExistsProblem;
import io.lalahtalks.user.gateway.client.http.contract.problem.AccountNotFoundProblem;
import io.lalahtalks.user.gateway.server.domain.account.AccountAlreadyExistsException;
import io.lalahtalks.user.gateway.server.domain.account.AccountId;
import io.lalahtalks.user.gateway.server.domain.account.AccountNotFoundException;
import io.lalahtalks.user.gateway.server.domain.account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import reactor.core.publisher.Mono;

import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayHttpPaths.MY_ACCOUNT_PATH;

@RestController
@RequestMapping(MY_ACCOUNT_PATH)
public class MyAccountController {

    private final AccountDtoMapper accountDtoMapper;
    private final AccountService accountService;

    public MyAccountController(AccountDtoMapper accountDtoMapper, AccountService accountService) {
        this.accountDtoMapper = accountDtoMapper;
        this.accountService = accountService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<AccountDto> get(@AuthenticationPrincipal JwtAuthenticationToken authToken) {
        var accountId = new AccountId(authToken.getName());
        return accountService.get(accountId)
                .map(accountDtoMapper::toDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    Mono<AccountCreatedDto> create(@RequestBody AccountCreationRequestDto requestDto) {
        var request = accountDtoMapper.fromDto(requestDto);
        return accountService.create(request)
                .map(accountDtoMapper::toDto);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Problem handle(AccountNotFoundException e) {
        return new AccountNotFoundProblem(e.getMessage());
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    Problem handle(AccountAlreadyExistsException e) {
        return new AccountAlreadyExistsProblem(e.getMessage());
    }

}
