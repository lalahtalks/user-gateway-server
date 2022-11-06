package io.lalahtalks.user.gateway.server.test;

import io.lalahtalks.user.gateway.client.dto.account.AccountCreatedDto;
import io.lalahtalks.user.gateway.client.dto.account.AccountCreationRequestDto;
import io.lalahtalks.user.gateway.client.dto.account.AccountDto;

import static io.lalahtalks.user.gateway.server.test.DataInstant.NOW;

public class DataAccount {

    public static final AccountDto ACCOUNT_1_DTO = new AccountDto(
            "account_1",
            "test@test.com",
            "User 1",
            NOW);

    public static final AccountCreationRequestDto ACCOUNT_CREATION_REQUEST_1_DTO = new AccountCreationRequestDto(
            "test@test.com",
            "User 1",
            "my_password");

    public static final AccountCreationRequestDto ACCOUNT_CREATION_REQUEST_2_DTO = new AccountCreationRequestDto(
            "already_exists@test.com",
            "User 1",
            "my_password");

    public static final AccountCreatedDto ACCOUNT_CREATED_DTO = new AccountCreatedDto("account_1", NOW);

    private DataAccount() {

    }

}
