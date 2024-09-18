package uz.developer.magistratura_ishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.magistratura_ishi.entity.ThemeEntity;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository extends JpaRepository<ThemeEntity, String> {

    Optional<ThemeEntity> findByStudentUsername(String  studentUsername);

    List<ThemeEntity> findByTeacherUsername(String teacherUsername);

    Optional<ThemeEntity> findByName(String name);
}
