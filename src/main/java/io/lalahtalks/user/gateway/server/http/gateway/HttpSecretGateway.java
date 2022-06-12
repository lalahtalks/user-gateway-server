package io.lalahtalks.user.gateway.server.http.gateway;

import io.lalahtalks.paging.domain.Page;
import io.lalahtalks.paging.domain.PageRequest;
import io.lalahtalks.paging.dto.PageDtoMapper;
import io.lalahtalks.secrets.client.dto.SecretDto;
import io.lalahtalks.secrets.client.http.SecretsHttpClient;
import io.lalahtalks.user.gateway.server.domain.account.AccountId;
import io.lalahtalks.user.gateway.server.domain.secret.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class HttpSecretGateway implements SecretGateway {

    private final PageDtoMapper pageDtoMapper;
    private final SecretsHttpClient secretsHttpClient;

    public HttpSecretGateway(PageDtoMapper pageDtoMapper, SecretsHttpClient secretsHttpClient) {
        this.pageDtoMapper = pageDtoMapper;
        this.secretsHttpClient = secretsHttpClient;
    }

    @Override
    public Mono<Page<Secret>> getPage(AccountId accountId, PageRequest request) {
        return secretsHttpClient.getPage(accountId.value(), request.pageNumber(), request.pageSize())
                .map(page -> pageDtoMapper.from(page, this::from));
    }

    private Secret from(SecretDto dto) {
        return new Secret(
                new SecretId(dto.id()),
                new SecretName(dto.name()),
                new SecretEncoded(dto.encoded()),
                dto.createdAt());
    }

}
