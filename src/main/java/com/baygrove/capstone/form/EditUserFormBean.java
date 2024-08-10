package com.baygrove.capstone.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EditUserFormBean {
    private Integer id;

    private String email;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
