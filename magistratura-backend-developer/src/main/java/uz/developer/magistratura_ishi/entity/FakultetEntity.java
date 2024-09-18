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
@Table(name = "fakultet")
public class FakultetEntity extends BaseEntity {
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dekan", referencedColumnName = "identifier")
    private TeacherEntity dekan;


    @OneToMany(mappedBy = "fakultetId", cascade = CascadeType.ALL)
    private Set<YunalishEntity> yunalishEntities;

    @OneToMany(mappedBy = "fakultetId", cascade = CascadeType.ALL)
    private Set<KafedraEntity> kafedraEntities;
}
