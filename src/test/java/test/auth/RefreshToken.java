package test.auth;

import endPoint.AuthEndpoint;
import endPoint.UserProfileEndpoint;
import io.restassured.response.ValidatableResponse;
import model.NewUser;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class RefreshToken {
    String email = "banana811@gmail.com";
    String password = "Window00$";

    @Steps
    private AuthEndpoint authEndpoint;
    private String refreshToken;
    private String accessToken;

    @Steps
    private UserProfileEndpoint userProfileEndpoint;

    @Before
    public void register() {
        NewUser newUser = NewUser.builder()
                .email(email)
                .password(password)
                .firstName("Peter")
                .lastName("Parker")
                .build();
        ValidatableResponse response = authEndpoint.register(newUser);
        refreshToken = response.extract().path("data.refreshToken");
    }

    @After
    public void deleteUser() {
        userProfileEndpoint.deleteUser(accessToken);
    }

    @Test
    public void refreshToken() {
        accessToken = authEndpoint.refreshToken(refreshToken);
    }
}
