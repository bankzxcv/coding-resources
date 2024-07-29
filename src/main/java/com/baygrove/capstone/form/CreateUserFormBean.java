package com.baygrove.capstone.form;

import com.baygrove.capstone.validation.UserEmailUnique;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserFormBean {

    private Integer id;

    @NotEmpty
    @UserEmailUnique(message = "This email is already in use")
    private String email;

    @NotEmpty
    private String password;
}
