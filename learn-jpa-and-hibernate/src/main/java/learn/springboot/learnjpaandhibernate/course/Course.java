package learn.springboot.learnjpaandhibernate.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course {
    @Id
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
}
