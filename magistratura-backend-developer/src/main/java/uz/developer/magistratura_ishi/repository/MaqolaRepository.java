package uz.developer.magistratura_ishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.magistratura_ishi.entity.MaqolaEntity;
import uz.developer.magistratura_ishi.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface MaqolaRepository extends JpaRepository<MaqolaEntity, String> {

    Optional<MaqolaEntity> findByName(String name);

    List<MaqolaEntity> findAllByStudentEntity(StudentEntity studentEntity);
}
