package com.restapi.Secutiry;

import com.restapi.Models.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MyUserDetails implements UserDetails {
    private MyUserDetails userDetails;

    public  MyUserDetails(){}
    public MyUserDetails(MyUserDetails userDetails){
        this.userDetails=userDetails;
    }
    private AppUser appUser=new AppUser();
    public MyUserDetails(AppUser user){
        this.appUser=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        System.out.println("this is getPass"+appUser.getPassword());
        if(appUser.getPassword()!=null)return appUser.getPassword();
        else return "not found";
    }

    @Override
    public String getUsername() {
        System.out.println("this is MyUserDetails "+appUser);
        return appUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
