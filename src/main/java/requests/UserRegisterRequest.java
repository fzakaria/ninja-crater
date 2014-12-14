package requests;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterRequest {

    @NotNull
    @NotBlank
    @Size(min=6, max=30)
    public String username;

    @NotNull
    @NotBlank
    @Size(min=6, max=30)
    public String password;

    @NotNull
    @NotBlank
    @Email
    public String email;

    public String firstName;

    public String lastName;
}
