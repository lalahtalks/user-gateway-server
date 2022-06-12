package io.lalahtalks.user.gateway.server.domain.secret;

import io.lalahtalks.paging.domain.Page;
import io.lalahtalks.paging.domain.PageRequest;
import io.lalahtalks.user.gateway.server.domain.account.AccountId;
import reactor.core.publisher.Mono;

public interface SecretGateway {

    Mono<Page<Secret>> getPage(AccountId accountId, PageRequest request);

}
