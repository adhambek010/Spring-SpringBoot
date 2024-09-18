package uz.developer.magistratura_ishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.magistratura_ishi.entity.KafedraEntity;

import java.util.Optional;

public interface KafedraRepository extends JpaRepository<KafedraEntity, String> {

    Optional<KafedraEntity> findByName(String kafedra);
}
