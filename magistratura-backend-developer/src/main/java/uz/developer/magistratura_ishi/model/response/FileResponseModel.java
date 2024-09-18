package uz.developer.magistratura_ishi.model.response;

import lombok.Data;

@Data
public class FileResponseModel {
    private String fileName;
    private String fileDownloadUri;
    private String createDate;
}
