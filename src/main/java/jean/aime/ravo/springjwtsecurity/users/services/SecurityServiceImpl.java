package jean.aime.ravo.springjwtsecurity.users.services;


import jean.aime.ravo.springjwtsecurity.users.domain.LocalUserDetails;
import jean.aime.ravo.springjwtsecurity.users.services.interfaces.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Jean Aimé Ravomanana
 * <p>
 * The implementation  of {@link SecurityService}
 */
@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Override
    public LocalUserDetails currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof LocalUserDetails)
            return (LocalUserDetails) authentication.getPrincipal();
        return null;
    }
}
