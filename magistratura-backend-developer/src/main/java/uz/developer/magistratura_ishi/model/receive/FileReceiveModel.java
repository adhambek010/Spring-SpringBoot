package uz.developer.magistratura_ishi.model.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileReceiveModel {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String username;
    private String subjectFileId;
}
