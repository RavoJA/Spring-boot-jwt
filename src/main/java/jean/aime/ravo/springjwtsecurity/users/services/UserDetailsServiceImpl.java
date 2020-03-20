package jean.aime.ravo.springjwtsecurity.users.services;


import jean.aime.ravo.springjwtsecurity.users.domain.LocalUser;
import jean.aime.ravo.springjwtsecurity.users.domain.LocalUserDetails;
import jean.aime.ravo.springjwtsecurity.users.domain.Roles;
import jean.aime.ravo.springjwtsecurity.users.repository.RoleRepository;
import jean.aime.ravo.springjwtsecurity.users.repository.UserRepository;
import jean.aime.ravo.springjwtsecurity.users.services.interfaces.UserRoleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * @author Jean Aimé Ravomanana
 * <p>
 * The implementation  of {@link UserRoleService}
 */
@Service
@Qualifier("userService")
public class UserDetailsServiceImpl implements UserRoleService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private static final String ID_CANNOT_BE_NULL = "Id cannot be null";

    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Optional<LocalUser> user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new IllegalArgumentException("Cannot find the user with username" + username);
        return new LocalUserDetails(user.get());
    }

    @Override
    @Transactional
    public LocalUser initUserToRole() {
        Roles vRoles = new Roles("Admin");
        Roles adminRole = roleRepository.save(vRoles);
        LocalUser vUser = new LocalUser("Admin", new BCryptPasswordEncoder().encode("1234"),
                "jeanaime-dev@it-mada.com");
        vUser.getRoles().add(adminRole);
        return  userRepository.save(vUser);
    }

    @Override
    public boolean noUserAdmin() {
        List<LocalUser> localUsers = userRepository.findAll();
        return localUsers.isEmpty();
    }

    @Override
    public List<LocalUser> listUser() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional
    public LocalUser saveUser(LocalUser localUser, List<Long> roleId) {
        checkBoolean(roleId.isEmpty(), "The user role cannt be null");
        checkBoolean(localUser.getUsername().isEmpty(), "The username cannot be null");
        List<Roles> listRole = roleRepository.findAllById(roleId);
        checkBoolean(listRole.isEmpty(), "No role found with these selected ids");
        localUser.setPassword(new BCryptPasswordEncoder().encode(localUser.getPassword()));
        localUser.getRoles().addAll(listRole);
        return userRepository.save(localUser);
    }

    @Override
    public LocalUser fetchOneById(Long id) {
        checkBoolean(id == null, ID_CANNOT_BE_NULL);
        return this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private void checkBoolean(boolean b, String s) {
        if (b) throw new IllegalArgumentException(s);
    }

    @Override
    public void deleteUserById(Long id) {
        checkBoolean(id == null, ID_CANNOT_BE_NULL);
        this.userRepository.deleteById(id);
    }

    @Override
    public boolean comparePassword(String password1, String password2) {
        checkBoolean(!password1.equals(password2), "Password not match");
        return true;
    }

    @Override
    @Transactional
    public LocalUser updatePassword(Long idUser, String actualPassword, String confirmPassword, String newPassword) {
        checkBoolean(idUser == null, ID_CANNOT_BE_NULL);
        LocalUser vLocalUser = this.fetchOneById(idUser);
        this.comparePassword(confirmPassword, actualPassword);
        boolean isMatched = new BCryptPasswordEncoder().matches(actualPassword, vLocalUser.getPassword());
        checkBoolean(!isMatched, "Invalid password ");
        vLocalUser.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        return userRepository.save(vLocalUser);
    }

    @Override
    @Transactional
    public LocalUser resetPassword(Long idUser) {
        checkBoolean(idUser == null, ID_CANNOT_BE_NULL);
        LocalUser vLocalUser = this.fetchOneById(idUser);
        vLocalUser.setPassword(new BCryptPasswordEncoder().encode("1234"));
        return userRepository.save(vLocalUser);
    }

    @PostConstruct
    private void executeDefaultAdmin() {
        if (noUserAdmin()) {
            initUserToRole();
        }
    }
}
