package com.cjq.springcloud.barrel.oauth.bean;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenjinquan
 */
@Data
public class User implements UserDetails {

    private Long userId;

    private String userName;

    private String passwd;

    private String salt;

    private List<String> roles;

    private boolean status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null) return null;
        List<SimpleGrantedAuthority> authorityList = roles.stream()
                .map(x -> new SimpleGrantedAuthority(x)).collect(Collectors.toList());
        return authorityList;
    }

    @Override
    public String getPassword() {
        return passwd;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return status;
    }

}
