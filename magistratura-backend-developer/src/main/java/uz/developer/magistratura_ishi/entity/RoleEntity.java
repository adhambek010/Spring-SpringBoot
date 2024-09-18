package uz.developer.magistratura_ishi.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;


@Data
@Entity
@Table(name = "roles")
public class RoleEntity implements GrantedAuthority {

    private static final long serialVersionUID = -1582007018827587161L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
