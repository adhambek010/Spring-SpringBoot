package uz.developer.magistratura_ishi.service.university;

import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.model.response.TeacherResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;
import uz.developer.magistratura_ishi.model.university.*;

import java.util.List;

@Service
public interface UniversityService {

    ApiResponse addFakulty(FakultetReceiveModel fakultetReceiveModel);

    ApiResponse addYunalish(YunalishReceiveModel yunalishReceiveModel);

    Object addKafedra(KafedraReceiveModel kafedraReceiveModel);

    List<FakultetResponseModel> getFakultetList();

    List<TeacherResponseModel> getFakultetDekanList();

    List<TeacherResponseModel> getKafedraMudiriList();

    List<YunalishResponseModel> getYunalishList();

    List<KafedraResponseModel> getKafedraList();

    FakultetResponseModel getFakultet(String fakultetId);

    YunalishResponseModel getYunalish(String yunalishId);

    KafedraResponseModel getKafedra(String kafedraId);

    ApiResponse editFakultet(FakultetReceiveModel fakultetReceiveModel);

    ApiResponse editYunalish(YunalishReceiveModel yunalishReceiveModel);
}
