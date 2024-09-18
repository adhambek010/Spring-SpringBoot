package uz.developer.magistratura_ishi.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developer.magistratura_ishi.model.receive.*;
import uz.developer.magistratura_ishi.service.impl.CalendarPlanServiceImpl;

@Slf4j
@RestController
@RequestMapping("/api/v1/calendar/plan")
@CrossOrigin(origins = "http://localhost:3000")
public class CalendarPlanController {

    @Autowired
    private CalendarPlanServiceImpl calendarPlanService;

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CalendarPlanReceiveModel planReceiveModel) {
        return ResponseEntity.ok(calendarPlanService.add(planReceiveModel));
    }

    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestHeader("studentUsername") String studentUsername) {
        return ResponseEntity.ok(calendarPlanService.list(studentUsername));
    }

    @PreAuthorize("hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @PostMapping("/edit")
    public ResponseEntity<?> edit(
            @RequestBody CalendarPlanDTO calendarPlanDTO) {
        return ResponseEntity.ok(calendarPlanService.editCalendarPlan(calendarPlanDTO));
    }

    @PreAuthorize("hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody CalendarPlanId calendarPlanId) {
        return ResponseEntity.ok(calendarPlanService.delete(calendarPlanId));
    }

    @PreAuthorize("hasAuthority('TEACHER')  or hasAuthority('ADMIN')")
    @GetMapping(value="/get/{calendarPlanId}")
    public CalendarPlanDTO getPlanTopicEntityById(
            @PathVariable("calendarPlanId") String calendarPlanId){
        return calendarPlanService.getCalendarTopicEntityById(calendarPlanId);
    }
}
