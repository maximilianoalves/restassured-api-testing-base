package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Step("Alterar uma reserva com token")
    public Response alterarUmaReservaComToken(int id) {
        return given()
                .header("Cookie", "token="+postAuthRequest.getToken())
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .body(Utils.payloadBooking().toString())
                .put("booking/" + id);
    }

}
