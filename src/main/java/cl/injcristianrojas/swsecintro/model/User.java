package cl.injcristianrojas.swsecintro.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class User implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final int user_type;

    public User(long id, String username, String password, int user_type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.user_type = user_type;
    }

    public User(long id, String username, int user_type) {
        this.id = id;
        this.username = username;
        this.password = "HIDDEN";
        this.user_type = user_type;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getUser_type() {
        return user_type;
    }
}
