package com.df.configuration.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by denis on 8/27/2016.
 */
public class Role implements GrantedAuthority {
    private String name;


    public Role(){}
    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonIgnore
    public String getAuthority() {
        return this.name;
    }
}
