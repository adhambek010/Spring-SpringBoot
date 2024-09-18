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
@Table(name = "yunalish")
public class YunalishEntity extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "yunalish", cascade = CascadeType.ALL)
    private Set<StudentEntity> studentEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fakultetId", insertable = false, updatable = false)
    private FakultetEntity fakultetEntity;
    private String fakultetId;
}
