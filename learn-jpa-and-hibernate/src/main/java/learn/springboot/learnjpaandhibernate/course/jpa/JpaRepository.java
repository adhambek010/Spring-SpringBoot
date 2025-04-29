package learn.springboot.learnjpaandhibernate.course.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import learn.springboot.learnjpaandhibernate.course.Course;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class JpaRepository {

    @PersistenceContext
    private EntityManager manager;

    public void insert(Course course) {
        manager.merge(course);
    }

    public Course findById(long id) {
        return manager.find(Course.class, id);
    }

    public void deleteById(long id){
        Course course = manager.find(Course.class, id);
        manager.remove(course);
    }
}
