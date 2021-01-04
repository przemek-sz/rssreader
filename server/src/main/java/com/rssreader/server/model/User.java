package com.rssreader.server.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<UserChannel> channelSet = new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<UserItem> useritemSet = new HashSet<>();



    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<UserChannel> getChannelSet() {
        return channelSet;
    }

    public void setChannelSet(Set<UserChannel> channelSet) {
        this.channelSet = channelSet;
    }

    public Set<UserItem> getUseritemSet() {
        return useritemSet;
    }

    public void setUseritemSet(Set<UserItem> useritemSet) {
        this.useritemSet = useritemSet;
    }
}
