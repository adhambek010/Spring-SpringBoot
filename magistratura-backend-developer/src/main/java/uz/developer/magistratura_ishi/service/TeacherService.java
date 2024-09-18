package uz.developer.magistratura_ishi.service;

import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.model.receive.TeacherReceiveModel;
import uz.developer.magistratura_ishi.model.response.StudentTopicInfoResponse;
import uz.developer.magistratura_ishi.model.response.TeacherResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;

import java.util.List;

@Service
public interface TeacherService {

    ApiResponse addTeacher(TeacherReceiveModel teacherReceiveModel);

    List<StudentTopicInfoResponse> getInfo(String teacherId);

    ApiResponse confirm(String planTopicId);

    TeacherResponseModel cabinet();

    List<TeacherResponseModel> list();

    List<TeacherResponseModel> search(String teacherUsername);

    List<TeacherResponseModel> listDekan();

    List<TeacherResponseModel> listRektorat();
}
