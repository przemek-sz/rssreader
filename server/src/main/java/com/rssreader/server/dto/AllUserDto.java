package com.rssreader.server.dto;

import org.springframework.stereotype.Component;

@Component
public class AllUserDto implements UserDto{

    private Long id;
    private String username;
    private String email;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
