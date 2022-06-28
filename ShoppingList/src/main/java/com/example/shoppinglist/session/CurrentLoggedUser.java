package com.example.shoppinglist.session;


import com.example.shoppinglist.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class CurrentLoggedUser {

    private Long id;

    private String username;


    public void login(User user){
        this.id = user.getId();
        this.username= user.getUsername();
    }
    public void logout(){
        this.id = null;
        this.username = null;
    }

    public Long getId() {
        return id;
    }

    public CurrentLoggedUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentLoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }
}
