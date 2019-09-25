package base.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;


/* INSERT INTO db_example.roles (id, role) values(1, 'ROLE_ADMIN');
INSERT INTO db_example.roles (id, role) values(2, 'ROLE_USER'); */


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority, Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role", nullable = false)
    private String role;
    public Role(){

    }
    public Role(String role){
        this.setRole(role);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
