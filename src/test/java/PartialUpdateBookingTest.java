import com.github.javafaker.Faker;
import com.google.gson.Gson;
import dto.PartialUpdateBooking;
import io.qameta.allure.Severity;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CreateToken;

import java.util.Locale;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/**
 * @author Flávia Bessa
 * @version 1.0
 * @since 27/01/2023
 * <p>
 * Classe responsável em enviar uma requisição PATCH para API
 * de booking com a intenção de atualizar parte das informações do booking.
 */

public class PartialUpdateBookingTest {
    public String jsonRequest = null;
    public String firstName;
    public String lastName;
    public Faker faker = new Faker(new Locale("PT", "BR"));
    PartialUpdateBooking partialUpdateBooking = new PartialUpdateBooking();
    Gson gson = new Gson();
    CreateBookingTest createBookingTest = new CreateBookingTest();
    CreateToken createToken = new CreateToken();

    @BeforeEach
    public void prepareBodyPartialUpdateBooking() {
        //Cria booking e armazena o id do booking
        createBookingTest.prepareBodyCreateBooking();
        createBookingTest.sendRequestCreateBooking();

        //Cria o token e armazena o token
        createToken.prepareBodyCreateToken();
        createToken.sendRequestCreateToken();

        //Gera Nome e Sobrenome aleatório
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();

        //Preeche os atributos nome e sobrenome com o valor gerado pelo javafaker
        partialUpdateBooking.setFirstname(firstName);
        partialUpdateBooking.setLastname(lastName);

        //Converte o objeto java para JSON
        jsonRequest = gson.toJson(partialUpdateBooking);
    }

    @Test
    @Severity(CRITICAL)
    public void sendRequestPartialUpdateBooking() {
        //Prenche o endpoint da API
        baseURI = "https://restful-booker.herokuapp.com/booking/" + createBookingTest.idBook.toString();
        //DADO
        given().filter(new AllureRestAssured())
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + createToken.token.toString())
                .body(jsonRequest)
                //QUANDO
                .when().patch()
                //ENTÃO
                .then()
                .assertThat()
                .statusCode(200)
                .body("firstname", Matchers.comparesEqualTo(firstName))
                .body("lastname", Matchers.comparesEqualTo(lastName))
                .body("totalprice", Matchers.comparesEqualTo(111))
                .body("depositpaid", Matchers.comparesEqualTo(true))
                .body("bookingdates.checkin", Matchers.notNullValue())
                .body("bookingdates.checkout", Matchers.notNullValue())
                .body("additionalneeds", Matchers.comparesEqualTo("Breakfast"));
    }


}
