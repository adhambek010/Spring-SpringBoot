package uz.developer.magistratura_ishi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developer.magistratura_ishi.model.response.StudentTopicInfoResponse;
import uz.developer.magistratura_ishi.service.impl.TeacherServiceImp;
import uz.developer.magistratura_ishi.validators.UserValidators;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/teacher")
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherRestController {

    @Autowired
    private TeacherServiceImp teacherService;

    @Autowired
    private UserValidators validators;

    @PreAuthorize("hasAuthority('TEACHER')")
    @GetMapping("/cabinet")
    public ResponseEntity<?> cabinet(){
        return ResponseEntity.ok(teacherService.cabinet());
    }

    /*@PostMapping("/change/password")
    public HttpEntity<?> changePassword(@RequestHeader String userId, @RequestBody ChangePasswordDTO changePasswordDTO){
        changePasswordDTO.setUserId(userId);

        ValidatorError validatorError = validators.validateUserPasswordChange(changePasswordDTO);
        if (!validatorError.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorError);
        }
        return ResponseEntity.ok(studentService.changePassword(changePasswordDTO));
    }

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
    }*/

    @PreAuthorize("hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @GetMapping("/getInfo")
    public List<StudentTopicInfoResponse> getInfo(
            @RequestHeader("teacherUsername") String teacherUsername) {
        return teacherService.getInfo(teacherUsername);
    }

    @PreAuthorize("hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @GetMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestHeader("planTopicId") String planTopicId) {
       return ResponseEntity.ok(teacherService.confirm(planTopicId));
    }
}
