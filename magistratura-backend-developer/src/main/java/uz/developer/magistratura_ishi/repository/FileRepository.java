package uz.developer.magistratura_ishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.magistratura_ishi.entity.FileEntity;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, String > {
    List<FileEntity> findAllByPlanTopicId(String plantopicId);
    List<FileEntity> findByMaqolaId(String maqolaId);

    Optional<FileEntity> findByFileName(String fileName);
}
