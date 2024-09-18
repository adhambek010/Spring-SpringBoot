package uz.developer.magistratura_ishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.magistratura_ishi.entity.RoleEntity;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);
}
