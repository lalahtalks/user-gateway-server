package io.lalahtalks.user.gateway.server;

import io.lalahtalks.user.gateway.client.dto.account.AccountDto;
import io.lalahtalks.user.gateway.server.test.ContextAware;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayHttpPaths.MY_ACCOUNT_PATH;
import static io.lalahtalks.user.gateway.server.test.DataAccessToken.ALL_MIGHTY;
import static io.lalahtalks.user.gateway.server.test.DataAccount.ACCOUNT_1_DTO;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class GetMyAccountTest extends ContextAware {

    @Test
    void it_works() {
        var response = given()
                .auth().preemptive().oauth2(ALL_MIGHTY)
                .with().accept(MediaType.APPLICATION_JSON_VALUE)
                .get(MY_ACCOUNT_PATH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(AccountDto.class);

        assertThat(response).isEqualTo(ACCOUNT_1_DTO);
    }

}
