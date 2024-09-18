package uz.developer.magistratura_ishi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.config.CurrentStudent;
import uz.developer.magistratura_ishi.entity.RoleEntity;
import uz.developer.magistratura_ishi.entity.StudentEntity;
import uz.developer.magistratura_ishi.entity.TeacherEntity;
import uz.developer.magistratura_ishi.entity.ThemeEntity;
import uz.developer.magistratura_ishi.model.receive.*;
import uz.developer.magistratura_ishi.model.response.GetThemeIdResponse;
import uz.developer.magistratura_ishi.model.response.StudentResponseModel;
import uz.developer.magistratura_ishi.model.response.UserResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;
import uz.developer.magistratura_ishi.repository.RoleRepository;
import uz.developer.magistratura_ishi.repository.StudentRepository;
import uz.developer.magistratura_ishi.repository.TeacherRepository;
import uz.developer.magistratura_ishi.repository.ThemeRepository;
import uz.developer.magistratura_ishi.service.StudentService;
import uz.developer.magistratura_ishi.service.base.BaseService;
import uz.developer.magistratura_ishi.service.file.FileService;

import java.util.*;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService, BaseService {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Value("${jwt.expiry.date}")
    private String jwtExpiryDate;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CurrentStudent currentStudent;

    @Autowired
    private FileService fileService;

    @Override
    public ApiResponse addStudent(StudentReceiveModel studentReceiveModel) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setIdentifier(UUID.randomUUID().toString());
        studentEntity.setFirstname(studentReceiveModel.getFirstname());
        studentEntity.setLastname(studentReceiveModel.getLastname());
        studentEntity.setMidname(studentReceiveModel.getMidname());
        studentEntity.setBirthDay(studentReceiveModel.getBirthday());
        studentEntity.setUsername(studentReceiveModel.getUsername());
        studentEntity.setKafedra(studentReceiveModel.getKafedra());
        studentEntity.setYunalish(studentReceiveModel.getYunalish());
        studentEntity.setStudentGroup(studentReceiveModel.getStudentGroup());
        studentEntity.setPassword(passwordEncoder.encode(studentReceiveModel.getPassword()));
        Optional<RoleEntity> authorityEntity = roleRepository.findByName("STUDENT");
        studentEntity.getRoles().add(authorityEntity.get());
        studentRepository.save(studentEntity);

        return SUCCESS;
    }

    @Override
    public ApiResponse edit(StudentEditReceiveModel studentReceiveModel) {
        log.info("======================== (Edit Student) StudentId:{} Student: {} ============================", studentReceiveModel.getUserId(), studentReceiveModel);
        Optional<StudentEntity> optionalStudent = studentRepository.findById(studentReceiveModel.getUserId());
        if (optionalStudent.isPresent()) {
            StudentEntity studentEntity = optionalStudent.get();
            studentEntity.setFirstname(studentReceiveModel.getFirstname());
            studentEntity.setLastname(studentReceiveModel.getLastname());
            studentEntity.setMidname(studentReceiveModel.getMidname());
            studentEntity.setKafedra(studentReceiveModel.getKafedra());
            studentEntity.setStudentGroup(studentReceiveModel.getStudentGroup());
            studentEntity.setUsername(studentReceiveModel.getUsername());
            studentRepository.save(studentEntity);
            log.info("+++++++++++ Student update success +++++++++++++++++++");
            return SUCCESS;
        } else {
            log.info("+++++++++++ Student update fail +++++++++++++++++++");
            return ERROR;
        }
    }

    @Override
    public UserResponseModel cabinet() {
        StudentEntity currentUser = this.currentStudent.getCurrentUser();
        Optional<StudentEntity> byStudentId = studentRepository.findByIdentifier(currentUser.getIdentifier());
        if (byStudentId.isEmpty()) {
            log.info("############################# Student not found! {} #####################################", byStudentId.get());
            return null;
        }
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setUserId(byStudentId.get().getIdentifier());
        userResponseModel.setUsername(byStudentId.get().getUsername());
        userResponseModel.setFirstname(byStudentId.get().getFirstname());
        userResponseModel.setLastname(byStudentId.get().getLastname());
        userResponseModel.setStudentGroup(byStudentId.get().getStudentGroup());
        userResponseModel.setEmail(byStudentId.get().getEmail());
        userResponseModel.setMidname(byStudentId.get().getMidname());
        userResponseModel.setPhoneNumber(byStudentId.get().getPhoneNumber());
        userResponseModel.setPhotoUrl(byStudentId.get().getPhoto());
        return userResponseModel;
    }

    @Override
    public ApiResponse changePassword(ChangePasswordDTO changePasswordDTO) {
        Optional<StudentEntity> byStudentId = studentRepository.findByUsername(changePasswordDTO.getStudentUsername());
        if (byStudentId.isEmpty()) {
            return USER_NOT_FOUND;
        }
        byStudentId.get().setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        studentRepository.save(byStudentId.get());
        return SUCCESS_V2;
    }

    @Override
    public ApiResponse update(UpdateDTO updateDTO) {
        Optional<StudentEntity> optionalStudent = studentRepository.findById(updateDTO.getUserId());
        if (optionalStudent.isEmpty()) {
            return USER_NOT_FOUND;
        }
        if (Objects.nonNull(updateDTO.getPhoneNumber()))
            optionalStudent.get().setPhoneNumber(updateDTO.getPhoneNumber());
        if (Objects.nonNull(updateDTO.getEmail()))
            optionalStudent.get().setEmail(updateDTO.getEmail());

        if (Objects.nonNull(updateDTO.getImageDTO()) && !updateDTO.getImageDTO().getBase64().isEmpty()) {
            String path = fileService.saveFile(updateDTO.getImageDTO().getBase64(), updateDTO.getImageDTO().getContentType());
            if (path == null)
                return ERROR_FILE_CREATED;
            optionalStudent.get().setPhoto(path);
        }
        studentRepository.save(optionalStudent.get());
        return SUCCESS_V2;
    }

    @Override
    public List<StudentResponseModel> getStudentList() {
        List<StudentEntity> studentEntityAll = studentRepository.findAll();

        List<StudentResponseModel> list = new ArrayList<>();
        studentEntityAll.forEach(studentEntity -> {
            StudentResponseModel studentResponseModel = new StudentResponseModel();
            studentResponseModel.setUserId(studentEntity.getIdentifier());
            studentResponseModel.setFirstname(studentEntity.getFirstname());
            studentResponseModel.setLastname(studentEntity.getLastname());
            studentResponseModel.setMidname(studentEntity.getMidname());
            studentResponseModel.setPassword(studentEntity.getPassword());
            studentResponseModel.setKafedra(studentEntity.getKafedraEntity().getName());
            studentResponseModel.setYunalish(studentEntity.getYunalishEntity().getName());
            studentResponseModel.setUsername(studentEntity.getUsername());
            studentResponseModel.setStudentGroup(studentEntity.getStudentGroup());
            Optional<ThemeEntity> optionalThemeEntity = themeRepository.findByStudentUsername(studentEntity.getUsername());
            optionalThemeEntity.ifPresent(themeEntity -> studentResponseModel.setTeacher(themeEntity.getTeacherUsername()));
            list.add(studentResponseModel);
        });
        return list;
    }

    @Override
    public UserResponseModel getUserName(String username) {
        Optional<StudentEntity> studentEntityAll = studentRepository.findByUsername(username);
        UserResponseModel userResponseModel = new UserResponseModel();
        studentEntityAll.ifPresent(studentEntity -> {
            userResponseModel.setUserId(studentEntity.getIdentifier());
            userResponseModel.setFirstname(studentEntity.getFirstname());
            userResponseModel.setLastname(studentEntity.getLastname());
            userResponseModel.setMidname(studentEntity.getMidname());
            userResponseModel.setKafedra(studentEntity.getKafedraEntity().getName());
            userResponseModel.setPassword(studentEntity.getPassword());
            userResponseModel.setUsername(studentEntity.getUsername());
            userResponseModel.setStudentGroup(studentEntity.getStudentGroup());
        });
        return userResponseModel;
    }

    @Override
    public ApiResponse delete(String deleteStudentId) {
        Optional<StudentEntity> optionalStudent = studentRepository.findById(deleteStudentId);
        if (optionalStudent.isEmpty()) {
            return STUDENT_NOT_FOUND;
        }
        if (optionalStudent.get().getIsActive()) {
            studentRepository.deleteById(deleteStudentId);
            return SUCCESS;
        }
        return ERROR;
    }

    @Override
    public GetThemeIdResponse getThemeId(String studentUsername) {
        Optional<ThemeEntity> themeEntity = themeRepository.findByStudentUsername(studentUsername);
        if (themeEntity.isEmpty()) {
            return null;
        }
        Optional<TeacherEntity> teacherEntity = teacherRepository.findByUsername(themeEntity.get().getTeacherUsername());
        if (teacherEntity.isEmpty()) {
            return null;
        }
        GetThemeIdResponse response = new GetThemeIdResponse();
        response.setThemeId(themeEntity.get().getIdentifier());
        response.setThemeName(themeEntity.get().getName());
        response.setActual(themeEntity.get().getActual());
        response.setTeacher(teacherEntity.get().getFirstname() + " " + teacherEntity.get().getLastname() + " " + teacherEntity.get().getMidname());
        return response;
    }


//    public List<UserResponseModel> filter(StudentFilter student) {
//        List<UserResponseModel> list = new ArrayList<>();
//        List<StudentEntity> searchList = studentRepository.findAll(StudentSpec.studentFilter(student));
//        searchList.forEach(studentEntity -> {
//            UserResponseModel model = new UserResponseModel();
//            model.setUserId(studentEntity.getIdentifier());
//            BeanUtils.copyProperties(studentEntity, model);
//            model.setPassword("");
//            list.add(model);
//        });
//        return list;
//    }

    @Override
    public ApiResponse themeActual(ThemeActualDTO themeActualDTO) {
        Optional<ThemeEntity> themeEntityOptional
                = themeRepository.findByStudentUsername(themeActualDTO.getUsername());
        if (themeEntityOptional.isPresent()) {
            ThemeEntity themeEntity = themeEntityOptional.get();
            themeEntity.setActual(themeActualDTO.getName());
            themeRepository.save(themeEntity);
            return SUCCESS;
        }
        return ERROR;
    }
}
