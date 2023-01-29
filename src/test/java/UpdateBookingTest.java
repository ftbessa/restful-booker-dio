import com.github.javafaker.Faker;
import com.google.gson.Gson;
import dto.Bookingdates;
import dto.CreateBooking;
import io.qameta.allure.Severity;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CreateToken;

import java.util.Locale;

import static io.qameta.allure.SeverityLevel.TRIVIAL;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/**
 * @author Flávia Bessa
 * @version 1.0
 * @since 27/01/2023
 * <p>
 * Classe responsável em enviar uma requisição PUT para API
 * de booking com a intenção de atualizar todas as informações do booking.
 */

public class UpdateBookingTest {

    public String firstName;
    public String lastName;
    public String jsonRequest = null;
    public Faker faker = new Faker(new Locale("PT", "BR"));
    CreateBooking createBooking = new CreateBooking();
    Bookingdates bookingdates = new Bookingdates();
    CreateBookingTest createBookingTest = new CreateBookingTest();
    CreateToken createToken = new CreateToken();
    Gson gson = new Gson();

    @BeforeEach
    public void prepareBodyUpdateBookingRequest() {
        //Cria booking e armazena o id do booking
        createBookingTest.prepareBodyCreateBooking();
        createBookingTest.sendRequestCreateBooking();

        //Cria o token e armazena o token
        createToken.prepareBodyCreateToken();
        createToken.sendRequestCreateToken();

        //Preenche o body request
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2018-01-01");

        firstName = faker.name().firstName();
        createBooking.setFirstname(firstName);
        lastName = faker.name().lastName();
        createBooking.setLastname(lastName);
        createBooking.setTotalprice(111);
        createBooking.setDepositpaid(true);
        createBooking.setBookingdates(bookingdates);
        createBooking.setAdditionalneeds("Breakfast");

        //Converte o objeto java para JSON
        jsonRequest = gson.toJson(createBooking);
    }

    @Test
    @Severity(TRIVIAL)
    public void sendRequestUpdateBooking() {
        //Prenche o endpoint da API
        baseURI = "https://restful-booker.herokuapp.com/booking/" + createBookingTest.idBook.toString();
        //DADO
        given().filter(new AllureRestAssured())
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + createToken.token.toString())
                .body(jsonRequest)
                //QUANDO
                .when().put()
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
        ;
    }
}
