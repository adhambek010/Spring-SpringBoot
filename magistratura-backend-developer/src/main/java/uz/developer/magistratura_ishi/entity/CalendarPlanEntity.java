package uz.developer.magistratura_ishi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.developer.magistratura_ishi.entity.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "calendar_plan")
public class CalendarPlanEntity extends BaseEntity {

    private String name;

    private String period;

    private String description;

    private String plan;

    @Column(name = "student_username")
    private String studentUsername;
    @Column(name = "teacher_username")
    private String teacherUsername;

}
