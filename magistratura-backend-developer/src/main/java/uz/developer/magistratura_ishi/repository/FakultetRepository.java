package uz.developer.magistratura_ishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.magistratura_ishi.entity.FakultetEntity;

import java.util.Optional;

public interface FakultetRepository extends JpaRepository<FakultetEntity, String> {

    Optional<FakultetEntity> findByName(String name);
}
