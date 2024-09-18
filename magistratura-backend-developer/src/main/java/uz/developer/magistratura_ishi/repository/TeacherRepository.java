package uz.developer.magistratura_ishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import uz.developer.magistratura_ishi.entity.StudentEntity;
import uz.developer.magistratura_ishi.entity.TeacherEntity;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity, String>, JpaSpecificationExecutor<TeacherEntity> {

    Optional<TeacherEntity> findByUsername(String username);
}
