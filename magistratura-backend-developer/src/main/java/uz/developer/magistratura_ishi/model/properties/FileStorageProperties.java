package uz.developer.magistratura_ishi.model.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;


}
