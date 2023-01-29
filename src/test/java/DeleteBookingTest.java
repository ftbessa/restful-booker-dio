import io.qameta.allure.Severity;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CreateToken;

import static io.qameta.allure.SeverityLevel.NORMAL;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/**
 * @author Flávia Bessa
 * @version 1.0
 * @since 27/01/2023
 * <p>
 * Classe responsável em enviar uma requisição de DELETE
 * para API DeleteBooking remover um booking da lista.
 */

public class DeleteBookingTest {

    CreateBookingTest createBookingTest = new CreateBookingTest();
    CreateToken createToken = new CreateToken();

    @BeforeEach
    public void prepareBodyDeleteBooking() {
        //Cria booking e armazena o id do booking
        createBookingTest.prepareBodyCreateBooking();
        createBookingTest.sendRequestCreateBooking();

        //Cria o token e armazena o token
        createToken.prepareBodyCreateToken();
        createToken.sendRequestCreateToken();
    }

    @Test
    @Severity(NORMAL)
    public void sendRequestDeleteBooking() {
        //Prenche o endpoint da API
        baseURI = "https://restful-booker.herokuapp.com/booking/" + createBookingTest.idBook.toString();
        //DADO
        given().filter(new AllureRestAssured())
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + createToken.token.toString())
                //QUANDO
                .when().delete()
                //ENTÃO
                .then()
                .assertThat()
                .statusCode(201);
    }
}
