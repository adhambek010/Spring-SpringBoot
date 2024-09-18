package uz.developer.magistratura_ishi.service.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.developer.magistratura_ishi.entity.MaqolaEntity;
import uz.developer.magistratura_ishi.entity.PlanTopicEntity;
import uz.developer.magistratura_ishi.model.properties.FileStorageProperties;
import uz.developer.magistratura_ishi.repository.MaqolaRepository;
import uz.developer.magistratura_ishi.repository.PlanTopicRepository;
import uz.developer.magistratura_ishi.service.exception.DateExpiredException;
import uz.developer.magistratura_ishi.service.exception.NoConfirmException;
import uz.developer.magistratura_ishi.validators.exception.FileStorageException;
import uz.developer.magistratura_ishi.validators.exception.MyFileNotFoundException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    PlanTopicRepository planTopicRepository;

    @Autowired
    MaqolaRepository maqolaRepository;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String planTopicFile(MultipartFile file, String planTopicId) {
        Optional<PlanTopicEntity> optionalPlanTopic = planTopicRepository.findById(planTopicId);
        Instant endDate = optionalPlanTopic.get().getEndDate().atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant instant = Instant.now();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            if (optionalPlanTopic.get().getActive() && instant.isAfter(endDate)) {
                throw new DateExpiredException("Vaifani bajarish muddati tugagan");
            }
            if (!optionalPlanTopic.get().getActive()){
                throw new NoConfirmException("Bu mavzu o'qtuvchi tomonidan tasdiqlanmagan!");
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public String maqolaFile(MultipartFile file, String maqolaId) {
//        Optional<MaqolaEntity> optionalPlanTopic = maqolaRepository.findById(maqolaId);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
