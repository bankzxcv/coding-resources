package com.baygrove.capstone.form;

import com.baygrove.capstone.validation.UserEmailUnique;
import com.baygrove.capstone.validation.UserUserNameUnique;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserFormBean {

    private Integer id;

    @NotEmpty
    @UserEmailUnique(message = "This email is already in use")
    private String email;

    @NotEmpty
    @UserUserNameUnique(message = "This username is already in use")
    private String username;

    @NotEmpty
    private String password;
}
