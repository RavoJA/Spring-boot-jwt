package jean.aime.ravo.springjwtsecurity.users.services.interfaces;


import jean.aime.ravo.springjwtsecurity.users.domain.LocalUserDetails;

/**
 * @author Jean Aimé Ravomanana
 */
public interface SecurityService {
    LocalUserDetails currentUser();
}
