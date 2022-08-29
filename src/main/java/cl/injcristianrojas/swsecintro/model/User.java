package cl.injcristianrojas.swsecintro.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @Column(name="id")
    private final Long id;

    @Column(name="username")
    private final String username;

    @Column(name="password")
    private final String password;

    @Column(name="user_type")
    private final int user_type;

    public User() {
        id = null;
        username = null;
        password = null;
        user_type = 0;
    }

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
