package uz.developer.magistratura_ishi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.entity.CalendarPlanEntity;
import uz.developer.magistratura_ishi.model.receive.CalendarPlanDTO;
import uz.developer.magistratura_ishi.model.receive.CalendarPlanId;
import uz.developer.magistratura_ishi.model.receive.CalendarPlanReceiveModel;
import uz.developer.magistratura_ishi.model.response.CalendarPlanResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;
import uz.developer.magistratura_ishi.repository.CalendarPlanRepository;
import uz.developer.magistratura_ishi.service.CalendarPlanService;
import uz.developer.magistratura_ishi.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CalendarPlanServiceImpl implements CalendarPlanService, BaseService {

    @Autowired
    private CalendarPlanRepository calendarPlanRepository;

    @Override
    public ApiResponse add(CalendarPlanReceiveModel planReceiveModel) {

        Optional<CalendarPlanEntity> optionalStudentEntity
                = calendarPlanRepository.findByName(planReceiveModel.getName());
        if (optionalStudentEntity.isPresent())
            return USER_EXIST;

        CalendarPlanEntity calendarPlanEntity = new CalendarPlanEntity();
        calendarPlanEntity.setIdentifier(UUID.randomUUID().toString());
        BeanUtils.copyProperties(planReceiveModel, calendarPlanEntity);
        calendarPlanRepository.save(calendarPlanEntity);
        return SUCCESS;
    }

    @Override
    public List<CalendarPlanResponseModel> list(String studentUsername) {
        List<CalendarPlanEntity> allCalendarPlanByStudentUsername
                = calendarPlanRepository.findAllCalendarPlanByStudentUsername(studentUsername);
        if (allCalendarPlanByStudentUsername != null) {
            List<CalendarPlanResponseModel> list = new ArrayList<>();
            allCalendarPlanByStudentUsername.forEach(calendarPlanEntity -> {
                CalendarPlanResponseModel responseModel=new CalendarPlanResponseModel();
                BeanUtils.copyProperties(calendarPlanEntity, responseModel);
                list.add(responseModel);
            });
            return list;
        }
        return null;
    }

    @Override
    public ApiResponse delete(CalendarPlanId calendarPlanId) {
        Optional<CalendarPlanEntity> entityOptional
                = calendarPlanRepository.findById(calendarPlanId.getCalendarPlanId());
        if (!entityOptional.isPresent()) {
            return CALENDAR_TOPIC_NOT_FOUND;
        }
        calendarPlanRepository.deleteById(calendarPlanId.getCalendarPlanId());
        return SUCCESS;
    }

    @Override
    public ApiResponse editCalendarPlan(CalendarPlanDTO calendarPlanDTO) {
        Optional<CalendarPlanEntity> optionalCalendarPlan
                = calendarPlanRepository.findById(calendarPlanDTO.getCalendarPlanId());
        if (optionalCalendarPlan.isPresent()) {
            CalendarPlanEntity calendarPlanEntity=optionalCalendarPlan.get();
            calendarPlanEntity.setName(calendarPlanDTO.getName());
            calendarPlanEntity.setPlan(calendarPlanDTO.getPlan());
            calendarPlanEntity.setDescription(calendarPlanDTO.getDescription());
            calendarPlanEntity.setPeriod(calendarPlanDTO.getPeriod());
            calendarPlanRepository.save(calendarPlanEntity);
            return SUCCESS;
        }
        return ERROR;
    }

    @Override
    public CalendarPlanDTO getCalendarTopicEntityById(String calendarPlanId) {
        Optional<CalendarPlanEntity> byId = calendarPlanRepository.findById(calendarPlanId);
        if (byId.isPresent()){
            CalendarPlanDTO calendarPlanDTO=new CalendarPlanDTO();
            calendarPlanDTO.setCalendarPlanId(byId.get().getIdentifier());
            calendarPlanDTO.setPlan(byId.get().getPlan());
            calendarPlanDTO.setName(byId.get().getName());
            calendarPlanDTO.setPeriod(byId.get().getPeriod());
            calendarPlanDTO.setDescription(byId.get().getDescription());
            return calendarPlanDTO;
        }
        return null;
    }
}
