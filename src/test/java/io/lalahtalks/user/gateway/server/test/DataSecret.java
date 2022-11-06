package io.lalahtalks.user.gateway.server.test;

import io.lalahtalks.paging.dto.PagingDto;
import io.lalahtalks.paging.dto.SortDto;
import io.lalahtalks.user.gateway.client.dto.secret.SecretDto;
import io.lalahtalks.user.gateway.client.dto.secret.SecretEncodedDto;
import io.lalahtalks.user.gateway.client.dto.secret.SecretPageDto;

import java.util.List;

import static io.lalahtalks.user.gateway.server.test.DataInstant.NOW;

public class DataSecret {

    public static final String SECRET_1_ID_VALUE = "secret_1";

    public static final String ENCODED_NAME_VALUE = "encoded_name";
    public static final String ENCODED_URL_VALUE = "encoded_url";
    public static final String ENCODED_USERNAME_VALUE = "encoded_username";
    public static final String ENCODED_PASSWORD_VALUE = "encoded_password";

    public static final SecretEncodedDto SECRET_1_ENCODED_DTO = new SecretEncodedDto(
            ENCODED_NAME_VALUE,
            ENCODED_URL_VALUE,
            ENCODED_USERNAME_VALUE,
            ENCODED_PASSWORD_VALUE
    );

    public static final SecretDto SECRET_1_DTO = new SecretDto(
            SECRET_1_ID_VALUE,
            SECRET_1_ENCODED_DTO,
            NOW
    );

    public static final SecretPageDto SECRET_PAGE_DTO = new SecretPageDto(
            new PagingDto(0, 25, 1L, 1),
            List.of(SECRET_1_DTO),
            SortDto.EMPTY
    );

    private DataSecret() {

    }

}
