package uz.developer.magistratura_ishi.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developer.magistratura_ishi.model.receive.StudentEditReceiveModel;
import uz.developer.magistratura_ishi.model.receive.StudentFilter;
import uz.developer.magistratura_ishi.model.receive.StudentReceiveModel;
import uz.developer.magistratura_ishi.model.response.StudentResponseModel;
import uz.developer.magistratura_ishi.model.response.UserResponseModel;
import uz.developer.magistratura_ishi.service.impl.PlanTopicServiceImpl;
import uz.developer.magistratura_ishi.service.impl.StudentServiceImpl;
import uz.developer.magistratura_ishi.validators.UserValidators;
import uz.developer.magistratura_ishi.validators.dataClass.ValidatorError;

import java.util.List;

@RestController
@RequestMapping("/api/admin/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    @Autowired
    private PlanTopicServiceImpl planTopicService;

    @Autowired
    private UserValidators userValidators;

    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody StudentReceiveModel studentReceiveModel){
        ValidatorError validatorError = userValidators.validateAddStudent(studentReceiveModel);
        if (!validatorError.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorError);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.addStudent(studentReceiveModel));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    public List<StudentResponseModel> listStudent(){
        return studentService.getStudentList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/planTopicList")
    public ResponseEntity<?> planTopicList(
            @RequestHeader("studentUsername") String studentUsername) {
        return ResponseEntity.ok(planTopicService.getPlanTopicListAdmin(studentUsername));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get/{studentUsername}")
    public ResponseEntity<?> getByUserName(
            @RequestHeader("studentUsername") String username) {
        return ResponseEntity.ok(studentService.getUserName(username));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestHeader("deleteStudentId") String deleteStudentId){
        return ResponseEntity.ok(studentService.delete(deleteStudentId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/filter")
    public List<UserResponseModel> filter(@RequestBody StudentFilter student){
        return studentService.filter(student);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody StudentEditReceiveModel editReceiveModel){
        return ResponseEntity.ok(studentService.edit(editReceiveModel));
    }
}
