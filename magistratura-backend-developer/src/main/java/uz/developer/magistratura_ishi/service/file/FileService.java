package uz.developer.magistratura_ishi.service.file;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {

    private String rootUrl = "./src/main/resources/static/image/student/";

    public String saveFile(String base64, String contentType) {
        try {
            File directory = new File(rootUrl);
            directory.mkdirs();
            String imageName = generateFileName() + contentType;
            if (base64.length()>0) {
                File file = new File(directory, imageName);
                file.createNewFile();
                byte[] decode = Base64.getDecoder().decode(base64);
                FileOutputStream fileOutputStream
                        = new FileOutputStream(file);
                fileOutputStream.write(decode);
                fileOutputStream.close();
            }
            return rootUrl + imageName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String generateFileName(){
        return UUID.randomUUID().toString();
    }
}



