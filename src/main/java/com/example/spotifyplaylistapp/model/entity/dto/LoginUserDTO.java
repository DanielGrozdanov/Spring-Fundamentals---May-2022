package com.example.spotifyplaylistapp.model.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginUserDTO {

    @NotBlank
    @Size(min = 3,max = 20)
    private String username;

    @NotBlank
    @Size(min = 3,max = 20)
    private String password;

    public String getUsername() {
        return username;
    }

    public LoginUserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginUserDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
