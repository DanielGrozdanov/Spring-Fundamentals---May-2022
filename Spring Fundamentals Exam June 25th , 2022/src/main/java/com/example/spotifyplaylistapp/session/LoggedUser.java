package com.example.spotifyplaylistapp.session;

import com.example.spotifyplaylistapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class LoggedUser {

    private Long id;

    private String username;

    public void login(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }
    public void logout(){
        this.username = null;
        this.id = null;
    }

    public Long getId() {
        return id;
    }

    public LoggedUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }
}
