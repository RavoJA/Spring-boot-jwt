package jean.aime.ravo.springjwtsecurity.users.security;

import jean.aime.ravo.springjwtsecurity.users.domain.LocalUser;
import jean.aime.ravo.springjwtsecurity.users.domain.LocalUserDetails;
import jean.aime.ravo.springjwtsecurity.users.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
/**
 * @author Jean Aimé Ravomanana
 * */
@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<LocalUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new LocalUserDetails(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Cannot find the user with username  %s", username));
        }
    }
}
