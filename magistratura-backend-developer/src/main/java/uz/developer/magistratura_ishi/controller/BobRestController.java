package uz.developer.magistratura_ishi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developer.magistratura_ishi.model.receive.BobDTO;
import uz.developer.magistratura_ishi.service.impl.BobServiceImpl;
import uz.developer.magistratura_ishi.validators.UserValidators;
import uz.developer.magistratura_ishi.validators.dataClass.ValidatorError;


@Slf4j
@RestController
@RequestMapping("/api/v1/bob")
@CrossOrigin(origins = "http://localhost:3000")
public class BobRestController {

    @Autowired
    private BobServiceImpl bobService;

    @Autowired
    private UserValidators userValidators;

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/add")
    public HttpEntity<?> addBob(@RequestHeader("studentUsername") String studentUsername,
                                @RequestBody BobDTO bobDTO) {
        bobDTO.setUsername(studentUsername);
        ValidatorError validatorError = userValidators.validateAddBob(bobDTO);
        if (!validatorError.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorError);
        }
        return ResponseEntity.ok(bobService.addBob(bobDTO));
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<?> bobList(
            @RequestHeader("studentUsername") String studentUsername) {
        return ResponseEntity.ok(bobService.getBobList(studentUsername));
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody BobDTO bobDTO) {
        return ResponseEntity.ok(bobService.editBob(bobDTO));
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @GetMapping(value = "/get/{bobId}")
    public BobDTO getByID(@PathVariable("bobId") String bobId) {
        return bobService.getById(bobId);
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{bobId}")
    public ResponseEntity<?> delete(@PathVariable("bobId") String bobId) {
        return ResponseEntity.ok(bobService.delete(bobId));
    }
}
