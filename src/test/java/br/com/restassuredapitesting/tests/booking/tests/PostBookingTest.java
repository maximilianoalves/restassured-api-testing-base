package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(Acceptance.class)
    @DisplayName("Criar uma nova reserva")
    public void validarCriarUmaReserva() throws Exception {
        postBookingRequest.criarReserva(Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 500 quando o payload da reserva estiver inválido")
    public void validarCriarUmaReservaPayloadInvalido() throws Exception {
        postBookingRequest.criarReserva(Utils.invalidPayloadBooking()).then()
                .statusCode(500)
                .time(lessThan(2L), TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category(E2e.class)
    @DisplayName("Validar a criacao de mais de um livro em sequencia")
    public void validarCriarMaisDeUmaReserva() throws Exception {
        postBookingRequest.criarReserva(Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
        postBookingRequest.criarReserva(Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Criar uma reserva enviando mais parametros no payload da reserva")
    public void validarCriarUmaReservaCampoExtraPayload() throws Exception {
        postBookingRequest.criarReserva(Utils.payloadBookingExtraField()).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 418 quando o header Accept for invalido")
    public void validarCriarUmaReservaComHeaderErrado() throws Exception {
        postBookingRequest.criarReserva(Utils.validPayloadBooking(), "application/joaquim").then()
                .statusCode(418)
                .time(lessThan(2L), TimeUnit.SECONDS);

    }


}
