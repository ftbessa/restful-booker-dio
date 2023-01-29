import io.qameta.allure.Severity;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.SeverityLevel.TRIVIAL;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/**
 * @author Flávia Bessa
 * @version 1.0
 * @since 27/01/2023
 * <p>
 * Classe responsável em enviar uma requisição GET para API
 * de Health Check com a intenção de verificar a disponibilidade
 * da API.
 */

public class HealthCheckTest {

    @Test
    @Severity(TRIVIAL)
    public void healthCheckAPIRequest() {
        //Prenche o endpoint da API
        baseURI = "https://restful-booker.herokuapp.com/ping";
        //DADO
        given().filter(new AllureRestAssured())
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                //QUANDO
                .when().get()
                //ENTÃO
                .then()
                .assertThat()
                .statusCode(201);
    }

}
