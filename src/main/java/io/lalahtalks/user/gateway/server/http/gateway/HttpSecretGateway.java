package io.lalahtalks.user.gateway.server.http.gateway;

import io.lalahtalks.paging.domain.Page;
import io.lalahtalks.paging.domain.PageRequest;
import io.lalahtalks.paging.dto.PageDtoMapper;
import io.lalahtalks.secrets.client.dto.SecretDto;
import io.lalahtalks.secrets.client.http.SecretsHttpClient;
import io.lalahtalks.user.gateway.server.domain.account.AccountId;
import io.lalahtalks.user.gateway.server.domain.secret.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpSecretGateway implements SecretGateway {

    private final PageDtoMapper pageDtoMapper;
    private final SecretsHttpClient secretsHttpClient;

    @Override
    public Page<Secret> getPage(AccountId accountId, PageRequest request) {
        var page = secretsHttpClient.getPage(accountId.getValue(), request.getPageNumber(), request.getPageSize());
        return pageDtoMapper.fromDto(page, this::fromDto);
    }

    private Secret fromDto(SecretDto dto) {
        return Secret.builder()
                .id(new SecretId(dto.getId()))
                .name(new SecretName(dto.getName()))
                .encoded(new SecretEncoded(dto.getEncoded()))
                .createdAt(dto.getCreatedAt())
                .build();
    }

}
