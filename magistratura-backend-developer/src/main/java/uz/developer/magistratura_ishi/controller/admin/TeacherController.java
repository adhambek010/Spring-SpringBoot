package uz.developer.magistratura_ishi.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developer.magistratura_ishi.model.receive.TeacherReceiveModel;
import uz.developer.magistratura_ishi.service.impl.TeacherServiceImp;
import uz.developer.magistratura_ishi.validators.UserValidators;
import uz.developer.magistratura_ishi.validators.dataClass.ValidatorError;

@RestController
@RequestMapping("/api/admin/teacher")
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherController {


    @Autowired
    private TeacherServiceImp teacherService;

    @Autowired
    private UserValidators userValidators;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addTeacher(@RequestBody TeacherReceiveModel teacherReceiveModel){
        ValidatorError validatorError = userValidators.validateAddTeacher(teacherReceiveModel);
        if (!validatorError.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorError);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.addTeacher(teacherReceiveModel));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/dekan/list")
    public ResponseEntity<?> teacherDekanList(){
        return ResponseEntity.ok(teacherService.listDekan());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/rektorat/list")
    public ResponseEntity<?> teacherRektoratList(){
        return ResponseEntity.ok(teacherService.listRektorat());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<?> teacherList(){
        return ResponseEntity.ok(teacherService.list());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<?> teacherSearch(
            @RequestHeader("teacherUsername") String teacherUsername){
        return ResponseEntity.ok(teacherService.search(teacherUsername));
    }
}
