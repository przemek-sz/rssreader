package com.rssreader.server.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtResponse {

    //private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private String username;
    private List<String> roles;
    private Date tokenexpirationdate;

    public JwtResponse(String jwttoken, String username, List<String> roles, Date tokenexpirationdate) {
        this.jwttoken = jwttoken;
        this.username = username;
        this.roles = roles;
        this.tokenexpirationdate = tokenexpirationdate;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Date getTokenexpirationdate() {
        return tokenexpirationdate;
    }

    public void setTokenexpirationdate(Date tokenexpirationdate) {
        this.tokenexpirationdate = tokenexpirationdate;
    }
}
