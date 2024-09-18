package uz.developer.magistratura_ishi.service;

import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.model.receive.ThemeReceiveModel;
import uz.developer.magistratura_ishi.model.response.ThemeResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;

import java.util.List;

@Service
public interface ThemeService {

    public ApiResponse addTheme(ThemeReceiveModel themeReceiveModel);

    List<ThemeResponseModel> list();

    ApiResponse edit(ThemeReceiveModel themeReceiveModel);

    ApiResponse delete(String themeId);

    ThemeResponseModel getThemeById(String themeId);
}
