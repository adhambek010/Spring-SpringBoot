package uz.developer.magistratura_ishi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.developer.magistratura_ishi.entity.RoleEntity;
import uz.developer.magistratura_ishi.model.properties.FileStorageProperties;
import uz.developer.magistratura_ishi.repository.RoleRepository;

import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class MagistraturaIshiApplication implements CommandLineRunner {
//    @Resource
//    FilesStorageService storageService;

    @Resource
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(MagistraturaIshiApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {

        List<String> roles= Arrays.asList("STUDENT", "TEACHER", "DEKAN", "PROREKTOR", "ADMIN", "KAFEDRA_MUDIRI");
        AtomicInteger counter= new AtomicInteger(1);
        roles.forEach(s -> {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setId(counter.get());
            roleEntity.setName(s);
            roleRepository.save(roleEntity);
            counter.getAndIncrement();
        });

//        storageService.deleteAll();
//        storageService.init();
    }
}
