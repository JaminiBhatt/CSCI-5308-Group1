package com.project.Group1.Bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;

    private Boolean isValidUser;

    private String password;

    private String RadioButton;

    private int roleId;

    private String role;

    private String email;

    private String isActive;

}
