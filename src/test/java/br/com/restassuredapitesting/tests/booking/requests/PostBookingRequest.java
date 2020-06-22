package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    @Step("Criar uma nova reserva")
    public Response criarReserva(JSONObject payload) {
        return given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .post("booking");
    }

    @Step("Criar uma nova reserva com header customizados")
    public Response criarReserva(JSONObject payload, String acceptHeader) {
        return given()
                .header("accept", acceptHeader)
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .post("booking");
    }
}
