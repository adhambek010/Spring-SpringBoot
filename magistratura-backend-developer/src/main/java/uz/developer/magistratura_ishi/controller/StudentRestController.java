package uz.developer.magistratura_ishi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developer.magistratura_ishi.model.receive.ChangePasswordDTO;
import uz.developer.magistratura_ishi.model.receive.ThemeActualDTO;
import uz.developer.magistratura_ishi.model.receive.UpdateDTO;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;
import uz.developer.magistratura_ishi.repository.PlanTopicRepository;
import uz.developer.magistratura_ishi.service.impl.StudentServiceImpl;
import uz.developer.magistratura_ishi.validators.UserValidators;
import uz.developer.magistratura_ishi.validators.dataClass.ValidatorError;

@Slf4j
@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentRestController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private UserValidators validators;

    @Autowired
    private PlanTopicRepository planTopicRepository;

    @PreAuthorize("hasAuthority('STUDENT')  or hasAuthority('ADMIN')")
    @GetMapping("/cabinet")
    public ResponseEntity<?> cabinet(){
        return ResponseEntity.ok(studentService.cabinet());
    }

    @PreAuthorize("hasAuthority('STUDENT')  or hasAuthority('ADMIN')")
    @PostMapping("/change/password")
    public HttpEntity<?> changePassword(
            @RequestHeader String studentUsername,
            @RequestBody ChangePasswordDTO changePasswordDTO){
        changePasswordDTO.setStudentUsername(studentUsername);

        ValidatorError validatorError = validators.validateUserPasswordChange(changePasswordDTO);
        if (!validatorError.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorError);
        }
        return ResponseEntity.ok(studentService.changePassword(changePasswordDTO));
    }

    @PreAuthorize("hasAuthority('STUDENT')  or hasAuthority('ADMIN')")
    @PostMapping("/update")
    public HttpEntity<?> update(@RequestHeader String userId, @RequestBody UpdateDTO updateDTO){
        updateDTO.setUserId(userId);

        ValidatorError validatorError = validators.validateUpdatePersonalInfo(updateDTO);
        if (!validatorError.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorError);
        }

        ApiResponse apiResponse = studentService.update(updateDTO);
        if (!apiResponse.isSuccess()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @GetMapping("/get-theme")
    public HttpEntity<?> getThemeId(@RequestHeader("studentUsername") String studentUsername){
        return ResponseEntity.ok(studentService.getThemeId(studentUsername));
    }

    @PreAuthorize("hasAuthority('STUDENT')  or hasAuthority('ADMIN')")
    @PostMapping("/add/mavzu-dolzarbligi")
    public HttpEntity<?> themaDolzarbligi(
            @RequestHeader("studentUsername") String studentUsername,
            @RequestBody ThemeActualDTO themeActualDTO){
        themeActualDTO.setUsername(studentUsername);
        if (themeActualDTO.getName().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("Mavzu dolzarbligini qo'shib bo'lmadi", false, 400));
        }
        return ResponseEntity.ok(studentService.themeActual(themeActualDTO));
    }
}
