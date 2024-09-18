package uz.developer.magistratura_ishi.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.entity.ThemeEntity;
import uz.developer.magistratura_ishi.model.receive.ThemeReceiveModel;
import uz.developer.magistratura_ishi.model.response.ThemeResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;
import uz.developer.magistratura_ishi.repository.ThemeRepository;
import uz.developer.magistratura_ishi.service.ThemeService;
import uz.developer.magistratura_ishi.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ThemeServiceImpl implements ThemeService, BaseService {

    @Autowired
    private ThemeRepository themeRepository;

    @Override
    public ApiResponse addTheme(ThemeReceiveModel themeReceiveModel) {
        Optional<ThemeEntity> byName = themeRepository.findByName(themeReceiveModel.getName());
        if (!byName.isPresent()) {
            ThemeEntity themeEntity = new ThemeEntity();
            themeEntity.setIdentifier(UUID.randomUUID().toString());
            themeEntity.setName(themeReceiveModel.getName());
            themeEntity.setDirection(themeReceiveModel.getDirection());
            themeEntity.setAcademicYear(themeReceiveModel.getAcademicYear());
            themeEntity.setStudentUsername(themeReceiveModel.getStudentUsername());
            themeEntity.setTeacherUsername(themeReceiveModel.getTeacherUsername());
            themeRepository.save(themeEntity);
            return SUCCESS;
        }
        return ERROR;
    }

    @Override
    public List<ThemeResponseModel> list() {
        List<ThemeEntity> repositoryAll = themeRepository.findAll();
        List<ThemeResponseModel> list = new ArrayList<>();
        repositoryAll.forEach(themeEntity -> {
            ThemeResponseModel model = new ThemeResponseModel();
            BeanUtils.copyProperties(themeEntity, model);
            list.add(model);
        });
        return list;
    }

    @Override
    public ThemeResponseModel getThemeById(String themeId) {
        Optional<ThemeEntity> optionalThemeEntity = themeRepository.findById(themeId);
        ThemeResponseModel themeResponseModel = new ThemeResponseModel();
        optionalThemeEntity.ifPresent(themeEntity -> {
            themeResponseModel.setIdentifier(themeEntity.getIdentifier());
            themeResponseModel.setName(themeEntity.getName());
            themeResponseModel.setAcademicYear(themeEntity.getAcademicYear());
            themeResponseModel.setDirection(themeEntity.getDirection());
            themeResponseModel.setStudentUsername(themeEntity.getStudentUsername());
            themeResponseModel.setTeacherUsername(themeEntity.getTeacherUsername());
        });
        return themeResponseModel;
    }

    @Override
    public ApiResponse edit(ThemeReceiveModel themeReceiveModel) {
        Optional<ThemeEntity> themeEntityOptional = themeRepository.findById(themeReceiveModel.getIdentifier());
        if (!themeEntityOptional.isPresent()) {
            return NOT_FOUND;
        }
        ThemeEntity themeEntity = themeEntityOptional.get();
        themeEntity.setName(themeReceiveModel.getName());
        themeEntity.setAcademicYear(themeReceiveModel.getAcademicYear());
        themeEntity.setDirection(themeReceiveModel.getDirection());
        themeEntity.setTeacherUsername(themeReceiveModel.getTeacherUsername());
        themeEntity.setStudentUsername(themeReceiveModel.getStudentUsername());

        themeRepository.save(themeEntity);
        return SUCCESS;
    }

    @Override
    public ApiResponse delete(String themeId) {
        Optional<ThemeEntity> themeEntityOptional = themeRepository.findById(themeId);
        if (!themeEntityOptional.isPresent()) {
            return NOT_FOUND;
        }

        themeRepository.deleteById(themeId);
        return SUCCESS;
    }
}
