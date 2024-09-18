package uz.developer.magistratura_ishi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.developer.magistratura_ishi.entity.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "file")
public class FileEntity extends BaseEntity {
    private LocalDateTime createDate;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String username;
    private String planTopicId;
    private String maqolaId;
}
