package uz.developer.magistratura_ishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.magistratura_ishi.entity.BobEntity;
import uz.developer.magistratura_ishi.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface BobRepository extends JpaRepository<BobEntity, String> {

    Optional<BobEntity> findByName(String name);
    Optional<BobEntity> findByNameOrNumber(String name, Integer number);

    List<BobEntity> findBobEntityByStudentEntity(StudentEntity studentEntity);
    List<BobEntity> findAllByStudentEntityOrderByNumber(StudentEntity studentEntity);
}
