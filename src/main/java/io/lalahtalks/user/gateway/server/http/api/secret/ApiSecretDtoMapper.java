package io.lalahtalks.user.gateway.server.http.api.secret;

import io.lalahtalks.secrets.client.dto.SecretCreatedDto;
import io.lalahtalks.user.gateway.client.dto.secret.SecretCreationRequestDto;
import io.lalahtalks.user.gateway.client.dto.secret.SecretDto;
import io.lalahtalks.user.gateway.client.dto.secret.SecretEncodedDto;
import io.lalahtalks.user.gateway.server.domain.secret.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class ApiSecretDtoMapper {

    SecretEncoded from(SecretEncodedDto dto) {
        var url = Optional.ofNullable(dto.url())
                .map(SecretUrl::new);
        var username = Optional.ofNullable(dto.username())
                .map(SecretUsername::new);
        return new SecretEncoded(
                new SecretName(dto.name()),
                url,
                username,
                new SecretPassword(dto.password())
        );
    }

    SecretCreationRequest from(SecretCreationRequestDto dto) {
        var encoded = from(dto.encoded());
        return new SecretCreationRequest(encoded);
    }

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
                encoded.password().value());
    }

    SecretDto to(Secret secret) {
        var encoded = to(secret.encoded());
        return new SecretDto(
                secret.id().value(),
                encoded,
                secret.createdAt());
    }

    SecretCreatedDto to(SecretCreated created) {
        return new SecretCreatedDto(
                created.secretId().value(),
                created.createdAt());
    }

}
