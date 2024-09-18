package uz.developer.magistratura_ishi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.config.CurrentTeacher;
import uz.developer.magistratura_ishi.entity.*;
import uz.developer.magistratura_ishi.entity.base.Position;
import uz.developer.magistratura_ishi.model.receive.TeacherReceiveModel;
import uz.developer.magistratura_ishi.model.response.TeacherResponseModel;
import uz.developer.magistratura_ishi.model.response.StudentTopicInfoResponse;
import uz.developer.magistratura_ishi.model.response.StudentTopicListResponse;
import uz.developer.magistratura_ishi.model.response.UserResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;
import uz.developer.magistratura_ishi.repository.*;
import uz.developer.magistratura_ishi.repository.specs.TeacherSpec;
import uz.developer.magistratura_ishi.service.TeacherService;
import uz.developer.magistratura_ishi.service.base.BaseService;

import java.util.*;

@Slf4j
@Service
public class TeacherServiceImp implements TeacherService, BaseService {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Value("${jwt.expiry.date}")
    private String jwtExpiryDate;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PlanTopicRepository planTopicRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private CurrentTeacher currentTeacher;

    @Autowired
    private StudentRepository studentRepository;
    private TeacherEntity teacherEntity;

    @Override
    public ApiResponse addTeacher(TeacherReceiveModel teacherReceiveModel) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setIdentifier(UUID.randomUUID().toString());
        teacherEntity.setFirstname(teacherReceiveModel.getFirstname());
        teacherEntity.setLastname(teacherReceiveModel.getLastname());
        teacherEntity.setMidname(teacherReceiveModel.getMidname());
        teacherEntity.setUsername(teacherReceiveModel.getUsername());
        teacherEntity.setUnvoni(teacherReceiveModel.getUnvoni());
        teacherEntity.setPosition(Position.valueOf(teacherReceiveModel.getPosition()));
        teacherEntity.setPassword(passwordEncoder.encode(teacherReceiveModel.getPassword()));
        Optional<RoleEntity> authorityEntity = roleRepository.findByName("TEACHER");
        teacherEntity.getRoles().add(authorityEntity.get());
        teacherRepository.save(teacherEntity);
        return SUCCESS;
    }

    @Override
    public TeacherResponseModel cabinet() {
        TeacherEntity currentUser = this.currentTeacher.getCurrentUser();
        TeacherResponseModel userResponseModel = new TeacherResponseModel();
        userResponseModel.setUserId(currentUser.getIdentifier());
        userResponseModel.setPosition(currentUser.getPosition().name());
        BeanUtils.copyProperties(currentUser, userResponseModel);
        return userResponseModel;
    }

    @Override
    public List<StudentTopicInfoResponse> getInfo(String teacherUsername) {
        List<ThemeEntity> themeEntities = themeRepository.findByTeacherUsername(teacherUsername);
        if (themeEntities == null) {
            throw new RuntimeException(" Theme not found! ########################### TeacherUsername: " + teacherUsername + " ###########################");
        }
        List<StudentTopicInfoResponse> studentTopicInfoList = new ArrayList<>();
        themeEntities.forEach(themeEntity -> {
            Optional<StudentEntity> optionalStudent = studentRepository.findByUsername(themeEntity.getStudentUsername());
            StudentTopicInfoResponse studentTopicInfo = new StudentTopicInfoResponse();
            studentTopicInfo.setTheme(themeEntity.getName());
            UserResponseModel userResponseModel=new UserResponseModel();
            BeanUtils.copyProperties(optionalStudent.get(), userResponseModel);
            userResponseModel.setUserId(optionalStudent.get().getIdentifier());
            userResponseModel.setPassword("");
            studentTopicInfo.setStudent(userResponseModel);

            List<PlanTopicEntity> planTopicEntities = planTopicRepository.findByThemeEntity(themeEntity);
            if (planTopicEntities == null) {
                log.info("############################# PlanTopic not found! Theme: " + themeEntity + " #####################################");
                throw new UsernameNotFoundException(" PlanTopic not found!");
            }
            List<StudentTopicListResponse> topicListResponses = new ArrayList<>();
            planTopicEntities.forEach(planTopicEntity -> {
                StudentTopicListResponse studentTopicListResponse = new StudentTopicListResponse();
                studentTopicListResponse.setPlanTopicId(planTopicEntity.getIdentifier());
                studentTopicListResponse.setStartDate(planTopicEntity.getStartDate());
                studentTopicListResponse.setEndDate(planTopicEntity.getEndDate());
                studentTopicListResponse.setName(planTopicEntity.getName());
                topicListResponses.add(studentTopicListResponse);
                studentTopicInfo.setTopicList(topicListResponses);
            });
            studentTopicInfoList.add(studentTopicInfo);
        });
        return studentTopicInfoList;
    }

    @Override
    public ApiResponse confirm(String planTopicId) {
        TeacherEntity currentUser = this.currentTeacher.getCurrentUser();
        Optional<PlanTopicEntity> optionalPlanTopic = planTopicRepository.findById(planTopicId);
        if (optionalPlanTopic.isPresent()) {
            if (optionalPlanTopic.get().getActive()) {
                return ALREADY_CONFIRM;
            }
            String authority = currentUser.getRoles().toString();
            if (authority.contains("TEACHER")) {
                optionalPlanTopic.get().setActive(Boolean.TRUE);
            }
            planTopicRepository.save(optionalPlanTopic.get());
            return SUCCESS;
        }
        return PLAN_TOPIC_NOT_FOUND;
    }

    @Override
    public List<TeacherResponseModel> list() {
        List<TeacherEntity> repositoryAll = teacherRepository.findAll();
        List<TeacherResponseModel> list = new ArrayList<>();
        repositoryAll.forEach(entity -> {
            TeacherResponseModel model = new TeacherResponseModel();
            model.setUserId(entity.getIdentifier());
            model.setPosition(entity.getPosition().name());
            BeanUtils.copyProperties(entity, model);
            list.add(model);
        });
        return list;
    }

    @Override
    public List<TeacherResponseModel> listDekan() {
        return getTeacherResponseModels(teacherRepository);
    }

    public static List<TeacherResponseModel> getTeacherResponseModels(TeacherRepository teacherRepository) {
        List<TeacherEntity> repositoryAll = teacherRepository.findAll();
        List<TeacherResponseModel> list = new ArrayList<>();
        repositoryAll.forEach(entity -> {
            TeacherResponseModel model = new TeacherResponseModel();
            if (entity.getPosition().equals(Position.DEKAN)){
                model.setUserId(entity.getIdentifier());
                model.setPosition(entity.getPosition().name());
                BeanUtils.copyProperties(entity, model);
                list.add(model);
            }
        });
        return list;
    }

    @Override
    public List<TeacherResponseModel> listRektorat() {
        List<TeacherEntity> repositoryAll = teacherRepository.findAll();
        List<TeacherResponseModel> list = new ArrayList<>();
        repositoryAll.forEach(entity -> {
            TeacherResponseModel model = new TeacherResponseModel();
            if (entity.getPosition().name().contains("PROREKTOR")){
                model.setUserId(entity.getIdentifier());
                model.setPosition(entity.getPosition().name());
                BeanUtils.copyProperties(entity, model);
                list.add(model);
            }
        });
        return list;
    }

    @Override
    public List<TeacherResponseModel> search(String teacherUsername) {
        List<TeacherResponseModel> list = new ArrayList<>();
        if (!teacherUsername.isEmpty()) {
            List<TeacherEntity> searchList = teacherRepository.findAll(TeacherSpec.teacherFilter(teacherUsername));
            searchList.forEach(teacherEntity1 -> {
                TeacherResponseModel model = new TeacherResponseModel();
                BeanUtils.copyProperties(teacherEntity1, model);
                model.setUserId(teacherEntity1.getIdentifier());
                model.setPosition(teacherEntity1.getPosition().name());
                list.add(model);
            });
        }
        return list;
    }
}
