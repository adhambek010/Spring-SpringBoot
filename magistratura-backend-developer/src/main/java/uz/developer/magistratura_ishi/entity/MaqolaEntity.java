package uz.developer.magistratura_ishi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.developer.magistratura_ishi.entity.base.BaseEntity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "maqola")
public class MaqolaEntity extends BaseEntity {

    @Column(columnDefinition = "text")
    private String name;

    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime lastUpdateTime;

    private String file;

    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentEntity")
    private StudentEntity studentEntity;
}
