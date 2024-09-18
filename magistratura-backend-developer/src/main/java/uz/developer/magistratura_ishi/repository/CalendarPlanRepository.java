package uz.developer.magistratura_ishi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.developer.magistratura_ishi.entity.CalendarPlanEntity;

import java.util.List;
import java.util.Optional;

public interface CalendarPlanRepository extends JpaRepository<CalendarPlanEntity, String> {

    Optional<CalendarPlanEntity> findByName(String name);

    @Query(value = "select * from calendar_plan where student_username=?1", nativeQuery = true)
    List<CalendarPlanEntity> findAllCalendarPlanByStudentUsername(String studentUsername);
}
