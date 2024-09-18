package uz.developer.magistratura_ishi.service;

import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.model.receive.BobDTO;
import uz.developer.magistratura_ishi.model.response.BobResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;

import java.util.List;

@Service
public interface BobService {

    ApiResponse addBob(BobDTO bobDTO);

    List<BobResponseModel> getBobList(String studentUsername);

    ApiResponse delete(String bobId);

    ApiResponse editBob(BobDTO bobDTO);

    BobDTO getById(String bobId);
}
