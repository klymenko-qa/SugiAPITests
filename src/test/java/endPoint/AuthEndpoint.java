package endPoint;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.NewUser;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class AuthEndpoint {
    private final static String VERIFY_EMAIL = "/auth/verify";
    private final static String LOGIN = "/auth/token";
    private final static String REGISTER = "/auth/register";


    static {
        SerenityRest.filters(new RequestLoggingFilter(LogDetail.ALL));
        SerenityRest.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    @Step
    public RequestSpecification given() {
        return SerenityRest
                .given()
                .baseUri("https://qa-sugi.uptech.team")
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse verifyEmail(String email) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("email", email)
                .header("Authorization", "Basic Y2xpZW50OmFHWXUyUFpWcXVaRDh5ZEU=")
                .when()
                .post(VERIFY_EMAIL)
                .then()
                .statusCode(SC_OK)
                .body("data.email", is (email));
    }

    public ValidatableResponse login(String username, String password) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("username", username)
                .queryParam("password", password)
                .queryParam("grant_type", "password")
                .header("Authorization", "Basic Y2xpZW50OmFHWXUyUFpWcXVaRDh5ZEU=")
                .when()
                .post(LOGIN)
                .then()
                .statusCode(SC_OK);
    }

    public String register(NewUser newUser) {
        ValidatableResponse response =
                given()
                .header("Authorization", "Basic Y2xpZW50OmFHWXUyUFpWcXVaRDh5ZEU=")
                .body(newUser)
                .when()
                .post(REGISTER)
                .then()
                .statusCode(SC_OK);
        return response.extract().path("accessToken");
    }
}
