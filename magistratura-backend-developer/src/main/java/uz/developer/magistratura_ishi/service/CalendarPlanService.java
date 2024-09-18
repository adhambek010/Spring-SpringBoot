package uz.developer.magistratura_ishi.service;

import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.model.receive.CalendarPlanDTO;
import uz.developer.magistratura_ishi.model.receive.CalendarPlanId;
import uz.developer.magistratura_ishi.model.receive.CalendarPlanReceiveModel;
import uz.developer.magistratura_ishi.model.response.CalendarPlanResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;

import java.util.List;

@Service
public interface CalendarPlanService {
    ApiResponse add(CalendarPlanReceiveModel planReceiveModel);

    List<CalendarPlanResponseModel> list(String teacherUsername);

    ApiResponse delete(CalendarPlanId calendarPlanId);

    ApiResponse editCalendarPlan(CalendarPlanDTO calendarPlanDTO);

    CalendarPlanDTO getCalendarTopicEntityById(String calendarPlanId);
}
