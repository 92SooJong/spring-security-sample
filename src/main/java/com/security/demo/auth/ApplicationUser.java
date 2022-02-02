package com.security.demo.auth;

import com.security.demo.security.ApplicationUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //private final Set<? extends GrantedAuthority> grantedAuthorities;
    private final String username;
    private final String password;
    private final String role;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

    public ApplicationUser(Long id, String username, String password, String role, boolean isAccountNonExpired,
                           boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role.equals(ApplicationUserRole.STUDENT.name())){
            return ApplicationUserRole.STUDENT.getGrantedAuthorities();
        }
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
