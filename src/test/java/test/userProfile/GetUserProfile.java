package test.userProfile;

import endPoint.AuthEndpoint;
import endPoint.UserProfileEndpoint;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetUserProfile {

    @Steps
    private AuthEndpoint authEndpoint;
    public String accessToken;

    @Steps
    private UserProfileEndpoint userProfileEndpoint;

    @Before
    public void login() {
        accessToken = authEndpoint.login("banana1@gmail.com", "Window1$");
    }

    @Test
    public void getUserProfile () {
        userProfileEndpoint.getUserProfile(accessToken);
        }
    }
