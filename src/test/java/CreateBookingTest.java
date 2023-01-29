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

import java.util.Locale;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/**
 * @author Flávia Bessa
 * @version 1.0
 * @since 27/01/2023
 * <p>
 * Classe responsável em enviar uma requisição POST para API
 * de cadastro de booking com todas as informações, dessa forma
 * um novo booking será cadastrado na lista.
 */

public class CreateBookingTest {

    public String firstName;
    public String lastName;
    public String idBook;
    public String jsonRequest = null;
    public Faker faker = new Faker(new Locale("PT", "BR"));
    CreateBooking createBooking = new CreateBooking();
    Bookingdates bookingdates = new Bookingdates();
    Gson gson = new Gson();

    @BeforeEach
    public void prepareBodyCreateBooking() {
        //Preenche o body request
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2018-01-01");

        firstName = faker.name().firstName();
        lastName = faker.name().lastName();

        createBooking.setFirstname(firstName);
        createBooking.setLastname(lastName);
        createBooking.setTotalprice(111);
        createBooking.setDepositpaid(true);
        createBooking.setBookingdates(bookingdates);
        createBooking.setAdditionalneeds("Breakfast");

        //Converte o objeto java para JSON
        jsonRequest = gson.toJson(createBooking);
    }

    @Test
    @Severity(CRITICAL)
    public void sendRequestCreateBooking() {
        //Prenche o endpoint da API
        baseURI = "https://restful-booker.herokuapp.com/booking";
        idBook =
                //DADO
                given().filter(new AllureRestAssured())
                        .baseUri(baseURI)
                        .contentType(ContentType.JSON)
                        .body(jsonRequest)
                        //QUANDO
                        .when().post()
                        //ENTÃO
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .body("bookingid", Matchers.notNullValue())
                        .extract().path("bookingid").toString();

    }

}
