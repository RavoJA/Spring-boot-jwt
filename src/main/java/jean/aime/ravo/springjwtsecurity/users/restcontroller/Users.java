package jean.aime.ravo.springjwtsecurity.users.restcontroller;



import jean.aime.ravo.springjwtsecurity.users.domain.LocalUser;
import jean.aime.ravo.springjwtsecurity.users.domain.UserRoleDto;
import jean.aime.ravo.springjwtsecurity.users.services.interfaces.UserRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/users")
public class Users {

    private final UserRoleService userRoleService;

    public Users(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }


    @GetMapping("list")
    public ResponseEntity<List<LocalUser>> list() {
        return new ResponseEntity<>(userRoleService.listUser(), HttpStatus.ACCEPTED);
    }

    @PostMapping("save-user")
    public ResponseEntity<LocalUser> saveUser(@RequestBody UserRoleDto userRoleDto) {
        LocalUser vLocalUser = new LocalUser();
        vLocalUser.setId(userRoleDto.getId());
        vLocalUser.setUsername(userRoleDto.getUsername());
        vLocalUser.setEmail(userRoleDto.getEmail());
        if (!userRoleService.comparePassword(userRoleDto.getPassword(), userRoleDto.getConfirmPassword()))
            throw new IllegalArgumentException("Password not match");
        vLocalUser.setPassword(userRoleDto.getPassword());
        return new ResponseEntity<>(userRoleService.saveUser(vLocalUser, userRoleDto.getRolesId()), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<LocalUser> list(@PathVariable Long id) {
        return new ResponseEntity<>(userRoleService.fetchOneById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("reset-password")
    public ResponseEntity<LocalUser> reset(@RequestBody UserRoleDto userRoleDto) {
        return new ResponseEntity<>(userRoleService.resetPassword(userRoleDto.getId()), HttpStatus.CREATED);
    }

    @PostMapping("update-password")
    public ResponseEntity<LocalUser> updatePwdUser(@RequestBody UserRoleDto roleDto) {
        return new ResponseEntity<>(userRoleService.updatePassword(roleDto.getId(), roleDto.getPassword(),
                roleDto.getConfirmPassword(), roleDto.getNewPassword()), HttpStatus.CREATED);
    }

}
