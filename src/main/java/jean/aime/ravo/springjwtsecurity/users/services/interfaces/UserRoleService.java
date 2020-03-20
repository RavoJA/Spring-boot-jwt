package jean.aime.ravo.springjwtsecurity.users.services.interfaces;


import jean.aime.ravo.springjwtsecurity.users.domain.LocalUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Jean Aimé Ravomanana
 * <p>
 * The service for managing the {@link LocalUser} entity
 */
public interface UserRoleService extends UserDetailsService {

    /**
     * Insert an default Admin user to database
     */
    LocalUser initUserToRole();

    /**
     * @return true if user_roles contains any row
     */
    boolean noUserAdmin();

    /**
     * Fetch all user
     */
    List<LocalUser> listUser();

    /**
     * Save new User with his roles
     *
     * @param localUser the user data
     * @param roleId    the roles lists
     */
    LocalUser saveUser(LocalUser localUser, List<Long> roleId);

    /**
     * fetch one user from database by the provided Id
     *
     * @param id user Id
     */
    LocalUser fetchOneById(Long id);

    /**
     * Deleting the user by his Id
     *
     * @param id user Id
     */
    void deleteUserById(Long id);

    /**
     * compare two password string
     *
     * @param password1
     * @param password2
     */
    boolean comparePassword(String password1, String password2);

    /**
     * Update password of the selected user ou current user
     *
     * @param idUser
     * @param actualPassword
     * @param newPassword
     * @param confirmPassword
     */
    LocalUser updatePassword(Long idUser, String actualPassword, String confirmPassword, String newPassword);

    /**
     * Reset password of the selected user
     *
     * @param idUser
     */
    LocalUser resetPassword(Long idUser);


}
