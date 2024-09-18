package uz.developer.magistratura_ishi.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.developer.magistratura_ishi.entity.base.BaseEntity;

import jakarta.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "student")
public class StudentEntity extends BaseEntity implements UserDetails {
    private Boolean isActive = true;
    private String firstname;
    private String lastname;
    private String midname;
    @Column(name = "birthDay")
    private String birthDay;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String photo;
    private String studentGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kafedra", insertable = false, updatable = false)
    private KafedraEntity kafedraEntity;
    private String kafedra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yunalish", insertable = false, updatable = false)
    private YunalishEntity yunalishEntity;
    private String yunalish;

    @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL)
    private Set<MaqolaEntity> maqolaEntities;

    @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL)
    private Set<BobEntity> bobEntities;

    @OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL)
    private Set<PlanTopicEntity> planTopicEntities;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "student_roles",
            joinColumns = {@JoinColumn(name = "studentId")},
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
