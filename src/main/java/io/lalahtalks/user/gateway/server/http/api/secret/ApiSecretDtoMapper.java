package io.lalahtalks.user.gateway.server.http.api.secret;

import io.lalahtalks.user.gateway.client.dto.SecretDto;
import io.lalahtalks.user.gateway.client.dto.SecretEncodedDto;
import io.lalahtalks.user.gateway.server.domain.secret.Secret;
import io.lalahtalks.user.gateway.server.domain.secret.SecretEncoded;
import io.lalahtalks.user.gateway.server.domain.secret.SecretUrl;
import io.lalahtalks.user.gateway.server.domain.secret.SecretUsername;
import org.springframework.stereotype.Component;

@Component
class ApiSecretDtoMapper {

    SecretEncodedDto to(SecretEncoded encoded) {
        var url = encoded.url()
                .map(SecretUrl::value)
                .orElse(null);
        var username = encoded.username()
                .map(SecretUsername::value)
                .orElse(null);
        return new SecretEncodedDto(
                encoded.name().value(),
                url,
                username,
                encoded.password().value()
        );
    }

    SecretDto to(Secret secret) {
        var encoded = to(secret.encoded());
        return new SecretDto(
                secret.id().value(),
                encoded,
                secret.createdAt());
    }

}
