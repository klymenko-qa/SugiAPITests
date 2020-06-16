package test.auth;

import endPoint.AuthEndpoint;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class LoginUser {

    @Steps
    private AuthEndpoint authEndpoint;

    @Test
    public void login() {
        authEndpoint.login("banana1@gmail.com", "Window1$");
    }
}
