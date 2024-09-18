package uz.developer.magistratura_ishi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.developer.magistratura_ishi.entity.base.BaseEntity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "bob")
public class BobEntity extends BaseEntity {

    private Integer number;
    private String name;
    private String xulosa;

    private LocalDateTime createDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentEntity")
    private StudentEntity studentEntity;

    @OneToMany(mappedBy = "bobEntity", cascade = CascadeType.ALL)
    private Set<PlanTopicEntity> planTopicEntities;
}
