package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.Contract;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das reservas")
    public void validarIdsDasReservas() throws Exception {
        getBookingRequest.allBookings().then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato do retorno da lista de reservas")
    public void garantirContratosListaReserva() throws Exception {
        getBookingRequest.allBookings().then()
                .statusCode(200)
                .assertThat()
                .body(
                        matchesJsonSchema(
                                new File(Utils.getContractsBasePath("booking", "bookings"))
                        )
                );
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro firstname")
    public void validarReservasUtilizandoFiltroFirstname() throws Exception {
        getBookingRequest.allBookings("firstname", "Mary").then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro lastname")
    public void validarReservasUtilizandoFiltroLastname() throws Exception {
        getBookingRequest.allBookings("lastname", "Ericsson").then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin")
    public void validarReservasUtilizandoFiltroCheckin() throws Exception {
        getBookingRequest.allBookings("checkin", "2015-06-21").then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout")
    public void validarReservasUtilizandoFiltroCheckout() throws Exception {
        getBookingRequest.allBookings("checkout", "2016-12-10").then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin and checkout")
    public void validarReservasUtilizandoFiltroCheckinECheckout() throws Exception {
        getBookingRequest.allBookings(
                "checkin", "2016-12-10",
                "checkout", "2019-09-10").then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro name, checkin and checkout date")
    public void validarReservasUtilizandoFiltroCheckinCheckoutEName() throws Exception {
        getBookingRequest.allBookings(
                "checkin", "2016-12-10",
                "checkout", "2019-09-10",
                "name", "Eric").then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(E2e.class)
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public void validarReservasUtilizandoFiltroMalFormatado() throws Exception {
        getBookingRequest.allBookings(
                "checkout", "12H^").then()
                .statusCode(500)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar uma reserva especifica")
    public void validarUmReservaEspecifica() throws Exception {
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        getBookingRequest.booking(primeiroId).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato do retorno de uma reserva específica")
    public void garantirContratoUmaReserva() throws Exception {
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        getBookingRequest.booking(primeiroId).then()
                .statusCode(200)
                .assertThat()
                .body(
                        matchesJsonSchema(
                                new File(Utils.getContractsBasePath("booking", "booking"))
                        )
                );
    }


}
