package io.lalahtalks.user.gateway.server.http.api.secret;

import io.lalahtalks.paging.domain.PageRequest;
import io.lalahtalks.paging.dto.PageDto;
import io.lalahtalks.paging.dto.PageDtoMapper;
import io.lalahtalks.secrets.client.dto.SecretCreatedDto;
import io.lalahtalks.user.gateway.client.dto.secret.SecretCreationRequestDto;
import io.lalahtalks.user.gateway.client.dto.secret.SecretDto;
import io.lalahtalks.user.gateway.server.domain.account.AccountId;
import io.lalahtalks.user.gateway.server.domain.secret.SecretService;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayHttpPaths.MY_SECRETS_PATH;

@RestController
@RequestMapping(MY_SECRETS_PATH)
public class MySecretsController {

    private final PageDtoMapper pageDtoMapper;
    private final ApiSecretDtoMapper secretDtoMapper;
    private final SecretService secretService;

    public MySecretsController(PageDtoMapper pageDtoMapper, ApiSecretDtoMapper secretDtoMapper, SecretService secretService) {
        this.pageDtoMapper = pageDtoMapper;
        this.secretDtoMapper = secretDtoMapper;
        this.secretService = secretService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<PageDto<SecretDto>> getPage(
            @AuthenticationPrincipal JwtAuthenticationToken authToken,
            @RequestParam("page") int pageNumber,
            @RequestParam("size") int pageSize) {
        var accountId = new AccountId(authToken.getName());
        var request = PageRequest.of(pageNumber, pageSize);
        return secretService.getPage(accountId, request)
                .map(page -> pageDtoMapper.to(page, secretDtoMapper::to));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<SecretCreatedDto> create(
            @AuthenticationPrincipal JwtAuthenticationToken authToken,
            @RequestBody SecretCreationRequestDto requestDto) {
        var accountId = new AccountId(authToken.getName());
        var request = secretDtoMapper.from(requestDto);
        return secretService.create(accountId, request)
                .map(secretDtoMapper::to);
    }

}
