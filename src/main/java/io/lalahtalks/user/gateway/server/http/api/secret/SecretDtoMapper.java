package io.lalahtalks.user.gateway.server.http.api.secret;

import io.lalahtalks.user.gateway.client.dto.SecretDto;
import io.lalahtalks.user.gateway.server.domain.secret.Secret;
import org.springframework.stereotype.Component;

@Component
class SecretDtoMapper {

    SecretDto to(Secret secret) {
        return new SecretDto(
                secret.id().value(),
                secret.name().value(),
                secret.encoded().value(),
                secret.createdAt());
    }

}
