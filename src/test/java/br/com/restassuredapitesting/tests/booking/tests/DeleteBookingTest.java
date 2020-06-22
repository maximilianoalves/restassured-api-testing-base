package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;

public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Excluir um reserva com sucesso")
    public void validarExcluirReserva() throws Exception {
        int idReservaCriada = postBookingRequest.criarReserva(Utils.validPayloadBooking()).then().statusCode(200).extract().path("bookingid");

        deleteBookingRequest.booking(idReservaCriada).then()
                .statusCode(201)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar excluir uma reserva que não existe")
    public void validarExcluirReservaInexistente() throws Exception {
        deleteBookingRequest.booking(999).then()
                .statusCode(405)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar excluir uma reserva sem autorização")
    public void validarExcluirReservaSemAuth() throws Exception {
        deleteBookingRequest.booking(999, "Basic bla bla bla").then()
                .statusCode(403)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
