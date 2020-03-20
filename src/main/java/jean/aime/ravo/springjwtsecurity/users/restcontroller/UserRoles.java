package jean.aime.ravo.springjwtsecurity.users.restcontroller;


import jean.aime.ravo.springjwtsecurity.users.domain.Roles;
import jean.aime.ravo.springjwtsecurity.users.services.interfaces.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class UserRoles {
    private final RoleService roleService;

    public UserRoles(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("list")
    public ResponseEntity<List<Roles>> list() {
        return new ResponseEntity<>(roleService.fetchAllRoles(), HttpStatus.ACCEPTED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Roles> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(roleService.fetchRoleById(id),
                HttpStatus.ACCEPTED);
    }

    @PostMapping("save-role")
    public ResponseEntity<Roles> saveRoles(@RequestBody String roleName) {
        Roles vRoles = new Roles();
        if (roleName.isEmpty()) throw new IllegalArgumentException("Role name is required");
        vRoles.setName(roleName);
        return new ResponseEntity<>(roleService.createRole(vRoles), HttpStatus.CREATED);
    }
}
