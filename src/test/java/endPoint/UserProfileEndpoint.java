package endPoint;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.apache.http.HttpStatus.SC_OK;

public class UserProfileEndpoint {
    private final static String GET_USER_PROFILE = "/api/me";
    private final static String DELETE_USER = "/api/me";

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

    public ValidatableResponse getUserProfile(String accessToken) {
        return given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(GET_USER_PROFILE)
                .then()
                .statusCode(SC_OK);
    }

    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(DELETE_USER)
                .then()
                .statusCode(SC_OK);
    }
}
