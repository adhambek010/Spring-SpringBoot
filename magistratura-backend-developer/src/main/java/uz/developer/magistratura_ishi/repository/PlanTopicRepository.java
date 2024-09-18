package uz.developer.magistratura_ishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.developer.magistratura_ishi.entity.PlanTopicEntity;
import uz.developer.magistratura_ishi.entity.ThemeEntity;

import java.util.List;
import java.util.Optional;

public interface PlanTopicRepository extends JpaRepository<PlanTopicEntity, String> {

    List<PlanTopicEntity> findByStudentUsernameOrderByCreateDateAsc(String username);

    List<PlanTopicEntity> findByThemeEntity(ThemeEntity studentEntity);

    Optional<PlanTopicEntity> findByName(String name);

    @Query(value = "select * from plan_topic where student_id=?1 order by last_update_date desc", nativeQuery = true)
    List<PlanTopicEntity> findByStudentId(String studentId);
}
