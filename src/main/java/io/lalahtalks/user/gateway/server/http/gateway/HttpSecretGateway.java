package io.lalahtalks.user.gateway.server.http.gateway;

import io.lalahtalks.paging.domain.Page;
import io.lalahtalks.paging.domain.PageRequest;
import io.lalahtalks.paging.dto.PageDtoMapper;
import io.lalahtalks.secrets.client.http.SecretsHttpClient;
import io.lalahtalks.user.gateway.server.domain.account.AccountId;
import io.lalahtalks.user.gateway.server.domain.secret.Secret;
import io.lalahtalks.user.gateway.server.domain.secret.SecretGateway;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class HttpSecretGateway implements SecretGateway {

    private final PageDtoMapper pageDtoMapper;
    private final SecretsHttpClient secretsHttpClient;
    private final SecretDtoMapper secretDtoMapper;

    public HttpSecretGateway(
            PageDtoMapper pageDtoMapper,
            SecretsHttpClient secretsHttpClient,
            SecretDtoMapper secretDtoMapper) {
        this.pageDtoMapper = pageDtoMapper;
        this.secretsHttpClient = secretsHttpClient;
        this.secretDtoMapper = secretDtoMapper;
    }

    @Override
    public Mono<Page<Secret>> getPage(AccountId accountId, PageRequest request) {
        return secretsHttpClient.getPage(accountId.value(), request.pageNumber(), request.pageSize())
                .map(page -> pageDtoMapper.from(page, secretDtoMapper::from));
    }

}
