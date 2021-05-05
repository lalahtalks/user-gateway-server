package io.lalahtalks.user.gateway.server.http.api.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountCreatedDto {

    @JsonProperty("accountId")
    public String accountId;

    @JsonProperty("createdAt")
    public Instant createdAt;

}
