package uz.developer.magistratura_ishi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developer.magistratura_ishi.model.UserSignInReceiveModel;
import uz.developer.magistratura_ishi.model.receive.PlanTopicId;
import uz.developer.magistratura_ishi.model.receive.PlanTopicDTO;
import uz.developer.magistratura_ishi.model.response.PlanTopicResponseModel;
import uz.developer.magistratura_ishi.service.impl.PlanTopicServiceImpl;
import uz.developer.magistratura_ishi.validators.UserValidators;
import uz.developer.magistratura_ishi.validators.dataClass.ValidatorError;

@Slf4j
@RestController
@RequestMapping("/api/v1/topic")
@CrossOrigin(origins = "http://localhost:3000")
public class PlanTopicRestController {

    @Autowired
    private PlanTopicServiceImpl planTopicService;
    @Autowired
    private UserValidators validators;

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody UserSignInReceiveModel userSignInReceiveModel) {
        return ResponseEntity.ok(planTopicService.login(userSignInReceiveModel));
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addTopic(
            @RequestHeader("studentUsername") String username,
            @RequestBody PlanTopicDTO planTopicDTO) {
        planTopicDTO.setUsername(username);
        ValidatorError validatorError = validators.validatePlanTopic(planTopicDTO);
        if (!validatorError.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorError);
        }
        return ResponseEntity.ok(planTopicService.addTopic(planTopicDTO));
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @GetMapping(value = "/get/{planTopicId}")
    public PlanTopicResponseModel getPlanTopicEntityById(
            @PathVariable("planTopicId") String planTopicId) {
        return planTopicService.getPlanTopicEntityById(planTopicId);
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> edit(
            @RequestHeader("username") String username,
            @RequestBody PlanTopicDTO planTopicDTO) {
        planTopicDTO.setUsername(username);
        return ResponseEntity.ok(planTopicService.editPlanTopic(planTopicDTO));
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<?> planTopicList(
            @RequestHeader("studentUsername") String studentUsername) {
        return ResponseEntity.ok(planTopicService.getPlanTopicList(studentUsername));
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody PlanTopicId planTopicId) {
        return ResponseEntity.ok(planTopicService.delete(planTopicId));
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @GetMapping("/pdf")
    public ResponseEntity<?> pdf(@RequestHeader("studentUsername") String studentUsername) {
        return ResponseEntity.ok(planTopicService.pdf(studentUsername));
    }

}
