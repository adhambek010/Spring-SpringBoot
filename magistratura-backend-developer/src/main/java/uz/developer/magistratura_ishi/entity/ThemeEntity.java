package uz.developer.magistratura_ishi.entity;

import lombok.Getter;
import lombok.Setter;
import uz.developer.magistratura_ishi.entity.base.BaseEntity;

import jakarta.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "theme")
public class ThemeEntity extends BaseEntity {

    private String name;
    private String academicYear;
    private String direction;
    @Column(name = "student_username")
    private String studentUsername;
    @Column(name = "teacher_username")
    private String  teacherUsername;

    @Column(columnDefinition = "text")
    private String actual;

    @OneToMany(mappedBy = "themeEntity", cascade = CascadeType.ALL)
    private Set<PlanTopicEntity> planTopicEntities;

}
