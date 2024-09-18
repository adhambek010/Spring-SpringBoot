package uz.developer.magistratura_ishi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developer.magistratura_ishi.model.receive.MaqolaDTO;
import uz.developer.magistratura_ishi.model.response.MaqolaResponseModel;
import uz.developer.magistratura_ishi.service.impl.MaqolaServiceImp;
import uz.developer.magistratura_ishi.validators.UserValidators;
import uz.developer.magistratura_ishi.validators.dataClass.ValidatorError;

@Slf4j
@RestController
@RequestMapping("/api/v1/maqola")
@CrossOrigin(origins = "http://localhost:3000")
public class MaqolaRestController {

    @Autowired
    private MaqolaServiceImp maqolaService;

    @Autowired
    private UserValidators userValidators;

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/add")
    public HttpEntity<?> addMaqola(@RequestHeader("studentUsername") String studentUsername,
                                   @RequestBody MaqolaDTO maqolaDTO) {
        maqolaDTO.setUsername(studentUsername);

        ValidatorError validatorError = userValidators.validateAddMaqola(maqolaDTO);
        if (!validatorError.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validatorError);
        }
        return ResponseEntity.ok(maqolaService.addMaqola(maqolaDTO));
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<?> maqolaList(
            @RequestHeader("studentUsername") String studentUsername) {
        return ResponseEntity.ok(maqolaService.getMaqolaList(studentUsername));
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @GetMapping(value = "/get/{maqolaId}")
    public MaqolaResponseModel getMaqolaById(
            @PathVariable("maqolaId") String maqolaId) {
        return maqolaService.getMaqolaById(maqolaId);
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> edit(
            @RequestHeader("username") String username,
            @RequestBody MaqolaDTO maqolaDTO) {
        maqolaDTO.setUsername(username);
        return ResponseEntity.ok(maqolaService.editMaqola(maqolaDTO));
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{maqolaId}")
    public ResponseEntity<?> delete(@PathVariable("maqolaId") String maqolaId) {
        return ResponseEntity.ok(maqolaService.delete(maqolaId));
    }
}
