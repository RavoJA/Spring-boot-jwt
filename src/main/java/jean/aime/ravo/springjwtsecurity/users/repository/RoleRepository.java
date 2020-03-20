package jean.aime.ravo.springjwtsecurity.users.repository;


import jean.aime.ravo.springjwtsecurity.users.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Jean Aimé Ravomanana
 * <p>
 * The Role Repository base class
 */
@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

    /**
     * Fetch one role from db by the provided name
     *
     * @param name
     */
    Optional<Roles> findByName(String name);
}
