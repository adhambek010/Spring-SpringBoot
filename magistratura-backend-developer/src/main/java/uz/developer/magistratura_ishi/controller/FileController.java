package uz.developer.magistratura_ishi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.developer.magistratura_ishi.model.receive.FileReceiveModel;
import uz.developer.magistratura_ishi.model.response.FileResponseModel;
import uz.developer.magistratura_ishi.model.response.UploadFileResponse;
import uz.developer.magistratura_ishi.service.FileEntityService;
import uz.developer.magistratura_ishi.service.file.FileStorageService;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/file")
@CrossOrigin(origins = "http://localhost:3000")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileEntityService fileEntityService;


    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/plan-topic/upload")
    public UploadFileResponse planTopicFile(
            @RequestHeader("username") String username,
            @RequestHeader("planTopicId") String planTopicId,
            @RequestParam("file") MultipartFile file){

        String fileName = fileStorageService.planTopicFile(file, planTopicId);
        String planTopicFileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/file/downloadFile/").path(fileName).toUriString();
        fileEntityService.isThereFileName(fileName);
        fileEntityService.saveplanTopicFile(new FileReceiveModel(fileName, planTopicFileDownloadUri, file.getContentType(),
                file.getSize(), username, planTopicId));
        return new UploadFileResponse(fileName, planTopicFileDownloadUri,
                file.getContentType(), file.getSize(), planTopicId, username);
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/maqola/upload")
    public UploadFileResponse maqolaFile(
            @RequestHeader("username") String username,
            @RequestHeader("maqolaId") String maqolaId,
            @RequestParam("file") MultipartFile file){

        String fileName = fileStorageService.maqolaFile(file, maqolaId);
        String maqolaFileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/file/downloadFile/").path(fileName).toUriString();
        fileEntityService.isThereFileName(fileName);
        fileEntityService.saveMaqolaFile(new FileReceiveModel(fileName, maqolaFileDownloadUri, file.getContentType(),
                file.getSize(), username, maqolaId));
        return new UploadFileResponse(fileName, maqolaFileDownloadUri,
                file.getContentType(), file.getSize(), maqolaId, username);
    }

    @GetMapping("/plan-topic/get")
    public List<FileResponseModel> getByTopicId(@RequestHeader("planTopicId") String plantopicId) {
        return fileEntityService.getByTopicId(plantopicId);
    }

    @GetMapping("/maqola/get")
    public List<FileResponseModel> getByMaqolaIdId(@RequestHeader("maqolaId") String maqolaId) {
        return fileEntityService.getByMaqolaId(maqolaId);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
