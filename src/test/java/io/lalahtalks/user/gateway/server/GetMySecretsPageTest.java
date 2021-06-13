package io.lalahtalks.user.gateway.server;

import io.lalahtalks.user.gateway.client.dto.SecretPageDto;
import io.lalahtalks.user.gateway.server.test.ContextAware;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayHttpPaths.MY_SECRETS_PATH;
import static io.lalahtalks.user.gateway.server.test.DataAccessToken.ALL_MIGHTY;
import static io.lalahtalks.user.gateway.server.test.DataSecret.SECRET_PAGE_DTO;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class GetMySecretsPageTest extends ContextAware {

    @Test
    void it_works() {
        var response = given()
                .auth().preemptive().oauth2(ALL_MIGHTY)
                .with().accept(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("page", 0)
                .queryParam("size", 25)
                .get(MY_SECRETS_PATH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().as(SecretPageDto.class);

        assertThat(response).isEqualTo(SECRET_PAGE_DTO);
    }

}
