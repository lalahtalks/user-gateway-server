package io.lalahtalks.user.gateway.server.domain.secret;

import io.lalahtalks.paging.domain.Page;
import io.lalahtalks.paging.domain.PageRequest;
import io.lalahtalks.user.gateway.server.domain.account.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecretService {

    private final SecretGateway secretGateway;

    public Page<Secret> getPage(AccountId accountId, PageRequest request) {
        return secretGateway.getPage(accountId, request);
    }

}
