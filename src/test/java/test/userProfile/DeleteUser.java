package test.userProfile;

import endPoint.AuthEndpoint;
import endPoint.UserProfileEndpoint;
import io.restassured.response.ValidatableResponse;
import model.NewUser;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class DeleteUser {
    @Steps
    private AuthEndpoint authEndpoint;
    public String accessToken;

    @Steps
    private UserProfileEndpoint userProfileEndpoint;

    @Before
    public void register() {
        NewUser newUser = NewUser.builder()
                .email("banana988@gmail.com")
                .password("Window00$")
                .firstName("Peter")
                .lastName("Parker")
                .build();
        ValidatableResponse response = authEndpoint.register(newUser);
        accessToken = response.extract().path("data.accessToken");    }

    @Test
    public void deleteUser() {
        userProfileEndpoint.deleteUser(accessToken);
    }
}
