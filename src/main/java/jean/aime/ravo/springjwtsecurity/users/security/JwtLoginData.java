package jean.aime.ravo.springjwtsecurity.users.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @author Jean Aimé Ravomanana
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtLoginData implements Serializable {
    private String username;
    private String password;
}
