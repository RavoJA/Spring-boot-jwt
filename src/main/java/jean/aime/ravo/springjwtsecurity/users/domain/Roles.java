package jean.aime.ravo.springjwtsecurity.users.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * THe Account Role entity
 *
 * @author Jean Aimé Ravomanana
 */
@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "localUsers")
public class Roles implements Serializable {

    /***
     *
     * the role identifier
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * THe role name
     * */
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<LocalUser> localUsers = new HashSet<>();

    public Roles(String name) {
        this.name = name;
    }


}
