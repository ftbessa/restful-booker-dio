import io.qameta.allure.Severity;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.SeverityLevel.NORMAL;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/**
 * @author Flávia Bessa
 * @version 1.0
 * @since 27/01/2023
 * <p>
 * Classe responsável em enviar uma requisição GET para buscar
 * uma lista de booking sem precisar informar o ID do booking
 * no parâmetro da API, dessa forma será retornado a lista completa de booking.
 */

public class GetBookingIdsTest {

    @Test
    @Severity(NORMAL)
    public void sendRequestGetBookingIds() {
        //Prenche o endpoint da API
        baseURI = "https://restful-booker.herokuapp.com/booking";
        //DADO
        given().filter(new AllureRestAssured())
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                //QUANDO
                .when().get()
                //ENTÃO
                .then()
                .assertThat()
                .statusCode(200);
    }

}
