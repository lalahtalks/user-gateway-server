package io.lalahtalks.user.gateway.server.http.gateway;

import io.lalahtalks.secrets.client.dto.SecretCreatedDto;
import io.lalahtalks.secrets.client.dto.SecretCreationRequestDto;
import io.lalahtalks.secrets.client.dto.SecretDto;
import io.lalahtalks.secrets.client.dto.SecretEncodedDto;
import io.lalahtalks.user.gateway.server.domain.secret.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecretDtoMapper {

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

    Secret from(SecretDto dto) {
        var encoded = from(dto.encoded());
        return new Secret(
                new SecretId(dto.id()),
                encoded,
                dto.createdAt());
    }

    SecretCreated from(SecretCreatedDto dto) {
        return new SecretCreated(
                new SecretId(dto.secretId()),
                dto.createdAt());
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

    SecretCreationRequestDto to(SecretCreationRequest request) {
        var encoded = to(request.encoded());
        return new SecretCreationRequestDto(encoded);
    }

}
