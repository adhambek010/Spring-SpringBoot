package uz.developer.magistratura_ishi.service;

import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.model.UserSignInReceiveModel;
import uz.developer.magistratura_ishi.model.receive.PlanTopicId;
import uz.developer.magistratura_ishi.model.receive.PlanTopicDTO;
import uz.developer.magistratura_ishi.model.response.PDFResponseModel;
import uz.developer.magistratura_ishi.model.response.PlanTopicResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;

import java.util.List;

@Service
public interface PlanTopicService {

    ApiResponse addTopic(PlanTopicDTO planTopicDTO);

    ApiResponse editPlanTopic(PlanTopicDTO planTopicDTO);

    List<PlanTopicResponseModel> getPlanTopicList(String studentId);

    ApiResponse delete(PlanTopicId planTopicId);

    PlanTopicResponseModel getPlanTopicEntityById(String id);

    ApiResponse login(UserSignInReceiveModel userSignInReceiveModel);

    List<PlanTopicResponseModel> getPlanTopicListAdmin(String studentUsername);

    PDFResponseModel pdf(String studentUsername);
}
