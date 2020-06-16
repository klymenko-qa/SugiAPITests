package test.auth;

import endPoint.AuthEndpoint;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class VerifyEmail {

    @Steps
    private AuthEndpoint authEndpoint;

    @Test
    public void verifyEmail() {
        authEndpoint.verifyEmail("banana1@gmail.com");
    }

}
