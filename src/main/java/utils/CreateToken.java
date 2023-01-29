package utils;

import com.google.gson.Gson;
import dto.Auth;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

/**
 * @author Flávia Bessa
 * @version 1.0
 * Classe responsável em gerar chave de token para as
 * demais APIS que necessitam consumir.
 * @since 27/01/2023
 */
public class CreateToken {

    public Auth auth = new Auth();
    public String jsonRequest = null;
    public String token;
    Gson gson = new Gson();

    @BeforeEach
    public void prepareBodyCreateToken() {
        //Preenche o body request
        auth.setUsername("admin");
        auth.setPassword("password123");

        //Converte o objeto java para JSON
        jsonRequest = gson.toJson(auth);
    }

    @Test
    public void sendRequestCreateToken() {
        baseURI = "https://restful-booker.herokuapp.com/auth";
        token =
                //DADO
                given().baseUri(baseURI)
                        .contentType(ContentType.JSON)
                        .body(jsonRequest)
                        //QUANDO
                        .when().post()
                        //ENTÃO
                        .then()
                        .assertThat()
                        .body("token", Matchers.notNullValue())
                        .body("token.length()", Matchers.is(15))
                        .body("token", Matchers.matchesRegex("^[a-z0-9]+$"))
                        .extract().path("token").toString();
    }
}
