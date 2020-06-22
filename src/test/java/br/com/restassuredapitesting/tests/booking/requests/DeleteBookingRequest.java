package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    @Step("Deletar uma reserva especifica")
    public Response booking(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete("booking/" + id);
    }

    @Step("Deletar uma reserva especifica")
    public Response booking(int id, String auth) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", auth)
                .when()
                .delete("booking/" + id);
    }
}
