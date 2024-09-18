package uz.developer.magistratura_ishi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.developer.magistratura_ishi.entity.base.BaseEntity;

import jakarta.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "kafedra")
public class KafedraEntity extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "yunalish", cascade = CascadeType.ALL)
    private Set<StudentEntity> studentEntities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kafedra_mudiri", referencedColumnName = "identifier")
    private TeacherEntity kafedraMudiri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fakultetId", insertable = false, updatable = false)
    private FakultetEntity fakultetEntity;
    private String fakultetId;
}
