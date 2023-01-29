import io.qameta.allure.Severity;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.SeverityLevel.TRIVIAL;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/**
 * @author Flávia Bessa
 * @version 1.0
 * @since 27/01/2023
 * <p>
 * Classe responsável em enviar uma requisição GET para
 * buscar um determinado booking informando o ID no parâmetro da API,
 * dessa forma será retornado um booking especifico
 * de acordo com ID informado no parâmetro da API
 */

public class GetBookingTest {

    CreateBookingTest createBookingTest = new CreateBookingTest();

    @BeforeEach
    public void prepareBodyGetBooking() {
        //Cria booking e armazena o id do booking
        createBookingTest.prepareBodyCreateBooking();
        createBookingTest.sendRequestCreateBooking();
    }

    @Test
    @Severity(TRIVIAL)
    public void sendRequestGetBooking() {
        //Prenche o endpoint da API
        baseURI = "https://restful-booker.herokuapp.com/booking/" + createBookingTest.idBook.toString();
        //DADO
        given().filter(new AllureRestAssured())
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                //QUANDO
                .when().get()
                //ENTÃO
                .then()
                .assertThat()
                .statusCode(200)
                .body("firstname", Matchers.comparesEqualTo(createBookingTest.firstName))
                .body("lastname", Matchers.comparesEqualTo(createBookingTest.lastName))
                .body("totalprice", Matchers.comparesEqualTo(111))
                .body("depositpaid", Matchers.comparesEqualTo(true))
                .body("bookingdates.checkin", Matchers.notNullValue())
                .body("bookingdates.checkout", Matchers.notNullValue())
                .body("additionalneeds", Matchers.comparesEqualTo("Breakfast"));
    }

}
