package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Buscar todas as reservas")
    public Response allBookings() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking");
    }

    @Step("Buscar todas as reservas com filtragem {param}={name}")
    public Response allBookings(String param, String name) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .queryParam(param, name)
                .get("booking");
    }

    @Step("Buscar todas as reservas com filtragem {param1}={name1} e {param2}={name2}")
    public Response allBookings(String param1, String name1, String param2, String name2) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .queryParam(param1, name1)
                .queryParam(param2, name2)
                .get("booking");
    }

    @Step("Buscar todas as reservas com filtragem {param1}={name1} , {param2}={name2} e {param3}={name3}")
    public Response allBookings(String param1, String name1, String param2, String name2, String param3, String name3) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .queryParam(param1, name1)
                .queryParam(param2, name2)
                .queryParam(param3, name3)
                .get("booking");
    }

    @Step("Buscar uma reserva especifica")
    public Response booking(int id) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking/" + id);
    }

}
