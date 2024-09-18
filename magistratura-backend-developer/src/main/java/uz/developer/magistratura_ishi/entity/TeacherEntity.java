package uz.developer.magistratura_ishi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.developer.magistratura_ishi.entity.base.BaseEntity;
import uz.developer.magistratura_ishi.entity.base.Position;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "teacher")
public class TeacherEntity extends BaseEntity implements UserDetails {

    private String firstname;
    private String lastname;
    private String midname;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String photo;
//    private String kafedra;
    private String unvoni;

    @OneToOne(mappedBy = "dekan")
    private FakultetEntity fakultetEntity;

    @OneToOne(mappedBy = "kafedraMudiri")
    private KafedraEntity kafedraEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "teacher_roles",
            joinColumns = {@JoinColumn(name = "teacherId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private Set<RoleEntity> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
