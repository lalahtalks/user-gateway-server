package io.lalahtalks.user.gateway.server;

import io.lalahtalks.user.gateway.client.dto.AccountCreatedDto;
import io.lalahtalks.user.gateway.server.test.ContextAware;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import static io.lalahtalks.user.gateway.client.http.contract.UserGatewayHttpPaths.ACCOUNTS_PATH;
import static io.lalahtalks.user.gateway.server.test.DataAccount.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class AccountCreationTest extends ContextAware {

    @Test
    void it_works() {
        var response = given()
                .with().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(ACCOUNT_CREATION_REQUEST_1_DTO)
                .post(ACCOUNTS_PATH)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(AccountCreatedDto.class);

        assertThat(response).isEqualTo(ACCOUNT_CREATED_DTO);
    }

    @Test
    void account_already_exists() {
        var response = given()
                .with().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(ACCOUNT_CREATION_REQUEST_2_DTO)
                .post(ACCOUNTS_PATH)
                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .extract().as(Problem.class);

        assertThat(response.getType()).hasToString("urn:lalahtalks:problem:user-gateway:account-already-exists");
        assertThat(response.getTitle()).isEqualTo("Account already exists");
        assertThat(response.getStatus()).isEqualTo(Status.CONFLICT);
    }

}
