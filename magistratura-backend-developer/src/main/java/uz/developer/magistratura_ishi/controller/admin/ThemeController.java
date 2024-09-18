package uz.developer.magistratura_ishi.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developer.magistratura_ishi.model.receive.ThemeReceiveModel;
import uz.developer.magistratura_ishi.model.response.MaqolaResponseModel;
import uz.developer.magistratura_ishi.model.response.ThemeResponseModel;
import uz.developer.magistratura_ishi.service.impl.ThemeServiceImpl;

@RestController
@RequestMapping("/api/admin/theme")
@CrossOrigin(origins = "http://localhost:3000")
public class ThemeController {

    @Autowired
    private ThemeServiceImpl themeService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addTheme(@RequestBody ThemeReceiveModel themeReceiveModel) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(themeService.addTheme(themeReceiveModel));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<?> themeList() {
        return ResponseEntity.ok(themeService.list());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/get/{themeId}")
    public ThemeResponseModel getThemeById(
            @PathVariable("themeId") String themeId) {
        return themeService.getThemeById(themeId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> edit(
            @RequestHeader("themeId") String themeId,
            @RequestBody ThemeReceiveModel themeReceiveModel) {
        themeReceiveModel.setIdentifier(themeId);
        return ResponseEntity.ok(themeService.edit(themeReceiveModel));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestHeader("themeId") String themeId) {
        return ResponseEntity.ok(themeService.delete(themeId));
    }
}
