package uz.developer.magistratura_ishi.service;

import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.model.receive.*;
import uz.developer.magistratura_ishi.model.response.GetThemeIdResponse;
import uz.developer.magistratura_ishi.model.response.StudentResponseModel;
import uz.developer.magistratura_ishi.model.response.UserResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;

import java.util.List;

@Service
public interface StudentService {

    ApiResponse addStudent(StudentReceiveModel studentReceiveModel);

    UserResponseModel cabinet();

    ApiResponse changePassword(ChangePasswordDTO changePasswordDTO);

    ApiResponse update(UpdateDTO updateDTO);

    List<StudentResponseModel> getStudentList();

    UserResponseModel getUserName(String username);

    ApiResponse delete(String deleteStudentId);

    GetThemeIdResponse getThemeId(String studentUsername);

    ApiResponse edit(StudentEditReceiveModel studentReceiveModel);

    ApiResponse themeActual(ThemeActualDTO themeActualDTO);
}
