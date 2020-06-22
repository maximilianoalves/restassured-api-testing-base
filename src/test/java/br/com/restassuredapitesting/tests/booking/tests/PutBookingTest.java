package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class PutBookingTest extends BaseTest {

    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Severity(SeverityLevel.NORMAL)
    @Test
    @Category({Acceptance.class, AllTests.class})
    @DisplayName("Alterar uma reserva somente utilizando o token")
    public void validarAlterarUmaReservaUtilizandoToken() throws Exception {
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComToken(primeiroId, postAuthRequest.getToken()).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Severity(SeverityLevel.NORMAL)
    @Test
    @Category({Acceptance.class, AllTests.class})
    @DisplayName("Alterar uma reserva somente utilizando Basic do Authorization")
    public void validarAlterarUmaReservaUtilizandoBasic() throws Exception {
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComBasic(primeiroId).then()
                .statusCode(200)
                .time(lessThan(3L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Severity(SeverityLevel.NORMAL)
    @Test
    @Category({E2e.class, AllTests.class})
    @DisplayName("Tentar alterar uma reserva quando o token não for enviado")
    public void validarAlterarUmaReservaSemUtilizarToken() throws Exception {
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComToken(primeiroId, "").then()
                .statusCode(403)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test
    @Category({E2e.class, AllTests.class})
    @DisplayName("Tentar alterar uma reserva quando o token enviado for inválido")
    public void validarAlterarUmaReservaComTokenInvalido() throws Exception {
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComToken(primeiroId, "bla bla bla").then()
                .statusCode(403)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test
    @Category({E2e.class, AllTests.class})
    @DisplayName("Tentar alterar uma reserva que não existe")
    public void validarAlterarUmaReservaInexistente() throws Exception {

        putBookingRequest.alterarUmaReservaComToken(5000, postAuthRequest.getToken()).then()
                .statusCode(405)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
