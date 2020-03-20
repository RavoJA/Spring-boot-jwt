package jean.aime.ravo.springjwtsecurity.users.services.interfaces;


import jean.aime.ravo.springjwtsecurity.users.domain.Roles;

import java.util.List;

/**
 * @author Jean Aimé Ravomanana
 * <p>
 * The service for managing the {@link Roles} entity
 */
public interface RoleService {

    /**
     * Create role by the provided role object
     */
    Roles createRole(Roles roles);

    /**
     * Fetch one role by name
     */
    Roles fetchOneByName(String roleName);

    /**
     * Fetch one role by givens id
     */
    Roles fetchRoleById(Long id);

    /**
     * Fetch all roles
     */
    List<Roles> fetchAllRoles();

    /**
     * Delete the slected role by Id
     */
    void deleteRoleById(Long id);
}
