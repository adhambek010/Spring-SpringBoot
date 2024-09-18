package uz.developer.magistratura_ishi.model.response;

import lombok.Data;

@Data
public class GetThemeIdResponse {
    private String themeId;
    private String themeName;
    private String teacher;
    private String actual;

    public GetThemeIdResponse(String themeId) {
        this.themeId = themeId;
    }

    public GetThemeIdResponse() {
    }
}
