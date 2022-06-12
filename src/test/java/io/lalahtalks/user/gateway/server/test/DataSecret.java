package io.lalahtalks.user.gateway.server.test;

import io.lalahtalks.paging.dto.PagingDto;
import io.lalahtalks.paging.dto.SortDto;
import io.lalahtalks.user.gateway.client.dto.SecretDto;
import io.lalahtalks.user.gateway.client.dto.SecretPageDto;

import java.util.List;

import static io.lalahtalks.user.gateway.server.test.DataInstant.NOW;

public class DataSecret {

    public static final String SECRET_1_ID_VALUE = "secret_1";
    public static final String SECRET_1_NAME_VALUE = "Secret 1";
    public static final String SECRET_1_ENCODED_VALUE = "some_encoded_value";

    public static final SecretDto SECRET_1_DTO = new SecretDto(
            SECRET_1_ID_VALUE,
            SECRET_1_NAME_VALUE,
            SECRET_1_ENCODED_VALUE,
            NOW);

    public static final SecretPageDto SECRET_PAGE_DTO = new SecretPageDto(
            new PagingDto(0, 25, 1L, 1),
            List.of(SECRET_1_DTO),
            SortDto.EMPTY);

    private DataSecret() {

    }

}
