package io.lalahtalks.user.gateway.server.domain.secret;

import lombok.NonNull;
import lombok.Value;

@Value
public class SecretEncoded {

    @NonNull String value;

}