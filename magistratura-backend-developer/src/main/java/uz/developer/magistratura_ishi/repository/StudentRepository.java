package uz.developer.magistratura_ishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uz.developer.magistratura_ishi.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, String>, JpaSpecificationExecutor<StudentEntity> {

    List<StudentEntity> findByUsernameOrFirstnameOrLastname(String username, String firstname, String lastname);

    Optional<StudentEntity> findByUsername(String  username);


    Optional<StudentEntity> findByIdentifier(String studentId);

}
