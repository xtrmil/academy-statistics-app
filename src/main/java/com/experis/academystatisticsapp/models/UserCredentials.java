package com.experis.academystatisticsapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserCredentials implements UserDetails {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;


    @JsonIgnore
    private String secret;
    private boolean isVerified;
    private boolean isAdmin;
    private List<GrantedAuthority> authorities;

    public UserCredentials(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.isAdmin = user.getIsAdmin();
        this.secret = user.getSecret();
        this.isVerified = user.getIsVerified();
        if(user.getIsAdmin()){
            this.authorities = Collections.singletonList( new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else{
            this.authorities =  Collections.singletonList( new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override
    @JsonIgnore
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return null;}

    public String getEmail(){
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSecret() {
        return secret;
    }

    public Boolean isVerified() {
        return isVerified;
    }

    public Boolean isAdmin(){ return isAdmin; }

    @Override
    public boolean isAccountNonExpired() { return false; }

    @Override
    public boolean isAccountNonLocked() { return false; }

    @Override
    public boolean isCredentialsNonExpired() { return false; }

    @Override
    public boolean isEnabled() { return false; }
}