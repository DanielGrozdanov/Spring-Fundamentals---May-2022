package com.example.shoppinglist.model.entity.dtos;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterUserDTO {

    @NotBlank
    @Size(min = 3,max = 20)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3,max = 20)
    private String password;

    @NotBlank
    @Size(min = 3,max = 20)
    private String confirmPassWord;

    public RegisterUserDTO() {

    }

    public String getUsername() {
        return username;
    }

    public RegisterUserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterUserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterUserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassWord() {
        return confirmPassWord;
    }

    public RegisterUserDTO setConfirmPassWord(String confirmPassWord) {
        this.confirmPassWord = confirmPassWord;
        return this;
    }

}
