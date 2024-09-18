package uz.developer.magistratura_ishi.service;

import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.model.receive.MaqolaDTO;
import uz.developer.magistratura_ishi.model.response.MaqolaResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;

import java.util.List;

@Service
public interface MaqolaService {

    ApiResponse addMaqola(MaqolaDTO maqolaDTO);

    List<MaqolaResponseModel> getMaqolaList(String studentUsername);

    ApiResponse delete(String maqolaId);

    MaqolaResponseModel getMaqolaById(String maqolaId);

    ApiResponse editMaqola(MaqolaDTO maqolaDTO);
}
