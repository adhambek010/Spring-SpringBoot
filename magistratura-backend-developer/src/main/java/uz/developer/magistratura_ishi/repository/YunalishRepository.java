package uz.developer.magistratura_ishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.magistratura_ishi.entity.YunalishEntity;

import java.util.Optional;

public interface YunalishRepository extends JpaRepository<YunalishEntity, String> {

    Optional<YunalishEntity> findByName(String yunalish);
}
