package uz.developer.magistratura_ishi.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developer.magistratura_ishi.model.response.TeacherResponseModel;
import uz.developer.magistratura_ishi.model.university.*;
import uz.developer.magistratura_ishi.service.university.UniversityServiceImpl;
import uz.developer.magistratura_ishi.validators.Validator;
import uz.developer.magistratura_ishi.validators.dataClass.ValidatorError;

import java.util.List;

@RestController
@RequestMapping("/api/admin/university")
@CrossOrigin(origins = "http://localhost:3000")
public class UniversityController {

    @Autowired
    private UniversityServiceImpl universityServiceImpl;

    @Autowired
    private Validator validator;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/fakultet/add")
    public ResponseEntity<?> addFakultet(@RequestBody FakultetReceiveModel fakultetReceiveModel) {
        ValidatorError validatorError = validator.validateAddFakultet(fakultetReceiveModel);
        if (!validatorError.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorError);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(universityServiceImpl.addFakulty(fakultetReceiveModel));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/yunalish/add")
    public ResponseEntity<?> addYunalish(@RequestBody YunalishReceiveModel yunalishReceiveModel) {
        ValidatorError validatorError = validator.validateAddYunalish(yunalishReceiveModel);
        if (!validatorError.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorError);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(universityServiceImpl.addYunalish(yunalishReceiveModel));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/kafedra/add")
    public ResponseEntity<?> addKafedra(@RequestBody KafedraReceiveModel kafedraReceiveModel) {
        ValidatorError validatorError = validator.validateAddKafedra(kafedraReceiveModel);
        if (!validatorError.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorError);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(universityServiceImpl.addKafedra(kafedraReceiveModel));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/fakultet/dekan/list")
    public List<TeacherResponseModel> listFacultyDekan() {
        return universityServiceImpl.getFakultetDekanList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/fakultet/kafedra-mudiri/list")
    public List<TeacherResponseModel> listKafedraMudiri() {
        return universityServiceImpl.getKafedraMudiriList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/fakultet/list")
    public List<FakultetResponseModel> listFaculty() {
        return universityServiceImpl.getFakultetList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/yunalish/list")
    public List<YunalishResponseModel> listYunalish() {
        return universityServiceImpl.getYunalishList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/kafedra/list")
    public List<KafedraResponseModel> listKafedra() {
        return universityServiceImpl.getKafedraList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/fakultet/get/{fakultetId}")
    public FakultetResponseModel getFaculty(@PathVariable("fakultetId") String fakultetId) {
        return universityServiceImpl.getFakultet(fakultetId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/yunalish/get/{yunalishId}")
    public YunalishResponseModel getYunalish(@PathVariable("yunalishId") String yunalishId) {
        return universityServiceImpl.getYunalish(yunalishId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/kafedra/get/{kafedraId}")
    public KafedraResponseModel getKafedra(@PathVariable("kafedraId") String kafedraId) {
        return universityServiceImpl.getKafedra(kafedraId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/fakultet/edit")
    public ResponseEntity<?> editFakultet(
            @RequestHeader("fakultetId") String fakultetId,
            @RequestBody FakultetReceiveModel fakultetReceiveModel) {
        fakultetReceiveModel.setIdentifier(fakultetId);
        return ResponseEntity.ok(universityServiceImpl.editFakultet(fakultetReceiveModel));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/yunalish/edit")
    public ResponseEntity<?> editYunalish(
            @RequestHeader("yunalishId") String yunalishId,
            @RequestBody YunalishReceiveModel yunalishReceiveModel) {
        yunalishReceiveModel.setIdentifier(yunalishId);
        return ResponseEntity.ok(universityServiceImpl.editYunalish(yunalishReceiveModel));
    }

}
