package jean.aime.ravo.springjwtsecurity.users.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Jean Aimé Ravomanana
 * */
@Slf4j
public class LocalUserDetails implements UserDetails {

    private final LocalUser localUser;

    public LocalUserDetails(LocalUser localUser) {
        this.localUser = localUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Roles role : localUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return localUser.getPassword();
    }

    @Override
    public String getUsername() {
        return localUser.getUsername();
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
        return localUser.isEnabled();
    }

    public String getEmaiAddress() {
        return localUser.getEmail();
    }


}
