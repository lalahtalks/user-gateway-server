package io.lalahtalks.user.gateway.server.http.api.secret;

import io.lalahtalks.user.gateway.client.dto.SecretDto;
import io.lalahtalks.user.gateway.server.domain.secret.Secret;
import org.springframework.stereotype.Component;

@Component
class SecretDtoMapper {

    SecretDto toDto(Secret secret) {
        return SecretDto.builder()
                .id(secret.getId().getValue())
                .name(secret.getName().getValue())
                .encoded(secret.getEncoded().getValue())
                .createdAt(secret.getCreatedAt())
                .build();
    }

}
