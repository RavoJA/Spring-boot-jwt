package jean.aime.ravo.springjwtsecurity.users.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jean Aimé Ravomanana
 *
 * The Data transfert Object for account data
 * */
@Data
public class UserRoleDto implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String confirmPassword;

    private String email;

    @JsonIgnore
    private String newPassword;

    private List<Long> rolesId = new ArrayList<>();

}
