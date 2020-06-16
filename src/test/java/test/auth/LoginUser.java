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
public class LoginUser {
    String email = "banana988@gmail.com";
    String password = "Window00$";

    @Steps
    private AuthEndpoint authEndpoint;
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
        accessToken = response.extract().path("data.accessToken");
    }

    @After
    public void deleteUser() {
        userProfileEndpoint.deleteUser(accessToken);
    }

    @Test
    public void login() {
        accessToken = authEndpoint.login(email, password);
    }
}
