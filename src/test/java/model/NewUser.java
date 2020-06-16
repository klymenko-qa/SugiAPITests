package model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NewUser {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
