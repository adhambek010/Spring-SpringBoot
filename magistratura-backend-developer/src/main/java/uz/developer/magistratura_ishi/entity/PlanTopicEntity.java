package uz.developer.magistratura_ishi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.developer.magistratura_ishi.entity.base.BaseEntity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "plan_topic")
public class PlanTopicEntity extends BaseEntity {

    @Column(name = "is_active", columnDefinition = "boolean default false", nullable = false)
    private Boolean active = false;

    private LocalDate startDate;
    private LocalDate endDate;
    @Column(columnDefinition = "text")
    private String name;
    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime lastUpdateDate;

    private String studentUsername;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity studentEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private ThemeEntity themeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bob_id")
    private BobEntity bobEntity;
}
