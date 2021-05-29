package io.lalahtalks.user.gateway.server.test;

import io.lalahtalks.user.gateway.client.dto.AccountCreatedDto;
import io.lalahtalks.user.gateway.client.dto.AccountCreationRequestDto;
import io.lalahtalks.user.gateway.client.dto.AccountDto;
import lombok.NoArgsConstructor;

import static io.lalahtalks.user.gateway.server.test.DataInstant.NOW;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DataAccount {

    public static final AccountDto ACCOUNT_1_DTO = AccountDto.builder()
            .id("account_1")
            .email("test@test.com")
            .username("User 1")
            .createdAt(NOW)
            .build();

    public static final AccountCreationRequestDto ACCOUNT_CREATION_REQUEST_1_DTO = AccountCreationRequestDto.builder()
            .email("test@test.com")
            .username("User 1")
            .password("my_password")
            .build();

    public static final AccountCreationRequestDto ACCOUNT_CREATION_REQUEST_2_DTO = AccountCreationRequestDto.builder()
            .email("already_exists@test.com")
            .username("User 1")
            .password("my_password")
            .build();

    public static final AccountCreatedDto ACCOUNT_CREATED_DTO = AccountCreatedDto.builder()
            .accountId("account_1")
            .createdAt(NOW)
            .build();

}
