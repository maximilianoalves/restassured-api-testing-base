package br.com.restassuredapitesting.tests.ping.tests;

import br.com.restassuredapitesting.suites.Healthcheck;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.ping.requests.GetPingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.lessThan;

public class GetPingTest extends BaseTest {

    GetPingRequest getPingRequest = new GetPingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Healthcheck.class)
    @DisplayName("Verificar se API está online")
    public void validarApiOnline() throws Exception {
        getPingRequest.ping().then()
                .statusCode(201)
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
