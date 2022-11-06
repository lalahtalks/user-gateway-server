package io.lalahtalks.user.gateway.server.domain.secret;

import io.lalahtalks.paging.domain.Page;
import io.lalahtalks.paging.domain.PageRequest;
import io.lalahtalks.user.gateway.server.domain.account.AccountId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SecretService {

    private final SecretGateway secretGateway;

    public SecretService(SecretGateway secretGateway) {
        this.secretGateway = secretGateway;
    }

    public Mono<Page<Secret>> getPage(AccountId accountId, PageRequest request) {
        return secretGateway.getPage(accountId, request);
    }

    public Mono<SecretCreated> create(AccountId accountId, SecretCreationRequest request) {
        return secretGateway.create(accountId, request);
    }

}
