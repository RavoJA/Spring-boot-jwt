package jean.aime.ravo.springjwtsecurity.users.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Jean Aimé
 * <p>
 * An application user base entity
 */
@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"roles"})
@AllArgsConstructor
public class LocalUser implements Serializable {

    /**
     * The username of thge account
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;

    /**
     * Account password
     */
    @JsonIgnore
    private String password;

    /**
     * The account email
     */
    private String email;

    /**
     * THe account profil picture data or link
     */
    private String profil;

    /**
     * Tell if the acci=ount is active
     */
    private boolean enabled = true;


    /**
     * All account Roles
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private Set<Roles> roles = new HashSet<>();

    public LocalUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LocalUser localUser = (LocalUser) o;
        return enabled == localUser.enabled &&
                Objects.equals(username, localUser.username) &&
                Objects.equals(password, localUser.password) &&
                Objects.equals(email, localUser.email) &&
                Objects.equals(profil, localUser.profil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, email, profil, enabled);
    }


}
