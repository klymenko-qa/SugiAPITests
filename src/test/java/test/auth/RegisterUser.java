package test.auth;

import endPoint.AuthEndpoint;
import model.NewUser;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class RegisterUser {

    @Steps
    private AuthEndpoint authEndpoint;

    @Test
    public void register() {
        NewUser newUser = NewUser.builder()
                .email("banana00@gmail.com")
                .password("Window00$")
                .firstName("Peter")
                .lastName("Parker")
                .build();
        authEndpoint.register(newUser);
    }
}
