package jean.aime.ravo.springjwtsecurity.users.services;



import jean.aime.ravo.springjwtsecurity.users.domain.Roles;
import jean.aime.ravo.springjwtsecurity.users.repository.RoleRepository;
import jean.aime.ravo.springjwtsecurity.users.services.interfaces.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * @author Jean Aimé Ravomanana
 * <p>
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Roles createRole(Roles roles) {
        return roleRepository.save(roles);
    }

    @Override
    public Roles fetchOneByName(String roleName) {
        if (roleName.isEmpty()) throw new IllegalArgumentException("Role name cannot be null");
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
    }

    @Override
    public Roles fetchRoleById(Long id) {
        if (id == null) throw new IllegalArgumentException("Role Id cannot be null");
        return roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
    }

    @Override
    public List<Roles> fetchAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
