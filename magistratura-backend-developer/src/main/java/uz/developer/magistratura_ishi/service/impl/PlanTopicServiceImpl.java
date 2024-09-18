package uz.developer.magistratura_ishi.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.developer.magistratura_ishi.entity.*;
import uz.developer.magistratura_ishi.entity.base.Position;
import uz.developer.magistratura_ishi.model.UserSignInReceiveModel;
import uz.developer.magistratura_ishi.model.receive.PlanTopicId;
import uz.developer.magistratura_ishi.model.receive.PlanTopicDTO;
import uz.developer.magistratura_ishi.model.response.PDFResponseModel;
import uz.developer.magistratura_ishi.model.response.PlanTopicResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;
import uz.developer.magistratura_ishi.repository.*;
import uz.developer.magistratura_ishi.service.PlanTopicService;
import uz.developer.magistratura_ishi.service.base.BaseService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class PlanTopicServiceImpl implements PlanTopicService, BaseService {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Value("${jwt.expiry.date}")
    private String jwtExpiryDate;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private PlanTopicRepository planTopicRepository;

    @Autowired
    private BobRepository bobRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public ApiResponse login(UserSignInReceiveModel userSignInReceiveModel) {
        if (userSignInReceiveModel.getUsername().contains("student")) {
            Optional<StudentEntity> optionalStudent
                    = studentRepository.findByUsername(userSignInReceiveModel.getUsername());

            if (!optionalStudent.isPresent())
                return USER_NOT_FOUND;

            if (!passwordEncoder.matches(userSignInReceiveModel.getPassword(), optionalStudent.get().getPassword())) {
                return ERROR_USERNAME_OR_PASSWORD;
            }
            Optional<ThemeEntity> themeEntity = themeRepository.findByStudentUsername(userSignInReceiveModel.getUsername());
            if (!themeEntity.isPresent()) {
                return THEME_NOT_FOUND;
            }
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", optionalStudent.get().getIdentifier());
            claims.put("themeId", themeEntity.get().getIdentifier());
            claims.put("roles", getRoles(optionalStudent.get().getRoles()));
            String token = this.generateStudentTokenToken(claims, optionalStudent.get());
            SUCCESS_V2.setToken(token);
            return SUCCESS_V2;
        } else {
            Optional<TeacherEntity> optionalTeacherEntity
                    = teacherRepository.findByUsername(userSignInReceiveModel.getUsername());
            if (!optionalTeacherEntity.isPresent()) {
                log.info("############################# Teacher not found! " + optionalTeacherEntity.get() + " #####################################");
                return USER_NOT_FOUND;
            }

            if (!passwordEncoder.matches(userSignInReceiveModel.getPassword(), optionalTeacherEntity.get().getPassword())) {
                return ERROR_USERNAME_OR_PASSWORD;
            }
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", optionalTeacherEntity.get().getIdentifier());
            claims.put("roles", getRoles(optionalTeacherEntity.get().getRoles()));
            String token = this.generateTeacherToken(claims, optionalTeacherEntity.get());
            SUCCESS_V2.setToken(token);
            return SUCCESS_V2;
        }
    }

    private String getRoles(Set<RoleEntity> roleEntities) {
        String roles = null;
        for (RoleEntity roleEntity : roleEntities) {
            if (Objects.isNull(roles)) roles = roleEntity.getName();
            else roles += "," + roleEntity.getName();
        }
        return roles;
    }

    private String generateStudentTokenToken(Map<String, Object> claims, StudentEntity studentEntity) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(studentEntity.getUsername())
                    .setId(String.valueOf(claims.get("userId")))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiryDate)))
                    .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                    .compact();

        } catch (Exception e) {
            return null;
        }
    }

    private String generateTeacherToken(Map<String, Object> claims, TeacherEntity teacherEntity) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(teacherEntity.getUsername())
                    .setId(String.valueOf(claims.get("userId")))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiryDate)))
                    .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                    .compact();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public ApiResponse addTopic(PlanTopicDTO planTopicDTO) {
        Optional<StudentEntity> optionalStudent = studentRepository.findByUsername(planTopicDTO.getUsername());
        if (!optionalStudent.isPresent()) {
            log.error("############################# Student not found! Student username: " + planTopicDTO.getUsername() + " #####################################");
            return USER_NOT_FOUND;
        }
        Optional<PlanTopicEntity> optionalPlanTopic = planTopicRepository.findByName(planTopicDTO.getName());
        if (optionalPlanTopic.isPresent())
            return PLAN_TOPIC_ALREADY_EXIST;
        PlanTopicEntity planTopicEntity = new PlanTopicEntity();
        planTopicEntity.setIdentifier(UUID.randomUUID().toString());
        planTopicEntity.setName(planTopicDTO.getName());
        planTopicEntity.setStartDate(planTopicDTO.getStartDate());
        planTopicEntity.setEndDate(planTopicDTO.getEndDate());
        planTopicEntity.setStudentUsername(planTopicDTO.getUsername());
        planTopicEntity.setLastUpdateDate(LocalDateTime.now());
        Optional<ThemeEntity> optionalThemeEntity = themeRepository.findById(planTopicDTO.getThemeId());
        if (!optionalThemeEntity.isPresent()) {
            log.error("############################# Theme not found! ThemeId: " + planTopicDTO.getThemeId() + " #####################################");
            return THEME_NOT_FOUND;
        }
        planTopicEntity.setThemeEntity(optionalThemeEntity.get());
        planTopicEntity.setStudentEntity(optionalStudent.get());
        Optional<BobEntity> optionalBob = bobRepository.findByName(planTopicDTO.getBobName());
        if (!optionalBob.isPresent()) {
            log.error("############################# Bob not found! bobName: " + planTopicDTO.getBobName() + " #####################################");
            return NOT_FOUND;
        }
        planTopicEntity.setBobEntity(optionalBob.get());
        planTopicRepository.save(planTopicEntity);

        optionalBob.get().getPlanTopicEntities().add(planTopicEntity);
        bobRepository.save(optionalBob.get());

        optionalStudent.get().getPlanTopicEntities().add(planTopicEntity);
        studentRepository.save(optionalStudent.get());

        optionalThemeEntity.get().getPlanTopicEntities().add(planTopicEntity);
        themeRepository.save(optionalThemeEntity.get());

        return SUCCESS;
    }

    @Override
    public PlanTopicResponseModel getPlanTopicEntityById(String id) {
        Optional<PlanTopicEntity> optionalPlanTopicEntity = planTopicRepository.findById(id);
        PlanTopicResponseModel planTopicResponseModel = new PlanTopicResponseModel();
        if (optionalPlanTopicEntity.isPresent()) {
            planTopicResponseModel.setIdentifier(optionalPlanTopicEntity.get().getIdentifier());
            planTopicResponseModel.setActive(optionalPlanTopicEntity.get().getActive());
            planTopicResponseModel.setName(optionalPlanTopicEntity.get().getName());
            planTopicResponseModel.setStartDate(optionalPlanTopicEntity.get().getStartDate());
            planTopicResponseModel.setEndDate(optionalPlanTopicEntity.get().getEndDate());
            planTopicResponseModel.setStudentUsername(optionalPlanTopicEntity.get().getStudentUsername());
            planTopicResponseModel.setThemeId(optionalPlanTopicEntity.get().getThemeEntity().getIdentifier());
            return planTopicResponseModel;
        }
        return null;
    }

    @Override
    public ApiResponse editPlanTopic(PlanTopicDTO planTopicDTO) {
        Optional<PlanTopicEntity> byId = planTopicRepository.findById(planTopicDTO.getPlanTopicId());
        if (!byId.isPresent()) {
            return PLAN_TOPIC_NOT_FOUND;
        }
        PlanTopicEntity planTopicEntity = byId.get();
        if (planTopicDTO.getUsername().contains("student")) {
            if (!byId.get().getActive()) {
                planTopicEntity.setName(planTopicDTO.getName());
                if (Objects.nonNull(planTopicDTO.getStartDate()))
                    planTopicEntity.setStartDate(planTopicDTO.getStartDate());
                if (Objects.nonNull(planTopicDTO.getEndDate()))
                    planTopicEntity.setEndDate(planTopicDTO.getEndDate());
                planTopicEntity.setLastUpdateDate(LocalDateTime.now());
                planTopicRepository.save(byId.get());
                return SUCCESS;
            } else {
                return CANNOT_BE_CHANGED;
            }
        } else {
            if (Objects.nonNull(planTopicDTO.getStartDate()))
                planTopicEntity.setStartDate(planTopicDTO.getStartDate());
            if (Objects.nonNull(planTopicDTO.getEndDate()))
                planTopicEntity.setEndDate(planTopicDTO.getEndDate());
            planTopicEntity.setName(planTopicDTO.getName());
            planTopicEntity.setLastUpdateDate(LocalDateTime.now());
            planTopicRepository.save(byId.get());
            return SUCCESS;
        }
    }

    @Override
    public List<PlanTopicResponseModel> getPlanTopicList(String username) {
        List<PlanTopicEntity> topicEntityList = planTopicRepository.findByStudentUsernameOrderByCreateDateAsc(username);
        List<PlanTopicResponseModel> list = new ArrayList<>();
        topicEntityList.forEach(planTopicEntity -> {
            PlanTopicResponseModel planTopicResponseModel = new PlanTopicResponseModel();
            planTopicResponseModel.setName(planTopicEntity.getName());
            planTopicResponseModel.setIdentifier(planTopicEntity.getIdentifier());
            planTopicResponseModel.setStartDate(planTopicEntity.getStartDate());
            planTopicResponseModel.setEndDate(planTopicEntity.getEndDate());
            List<FileEntity> planTopicFiles = fileRepository.findAllByPlanTopicId(planTopicEntity.getIdentifier());
            if ((planTopicEntity.getEndDate().minusDays(7).isBefore(LocalDate.now()) && LocalDate.now().isBefore(planTopicEntity.getEndDate())
                    || LocalDate.now().isEqual(planTopicEntity.getEndDate())) && !(planTopicFiles.size() > 0)) {
                planTopicResponseModel.setTimeIsNear(Boolean.TRUE);
            }
            if (planTopicEntity.getEndDate().isBefore(LocalDate.now())) {
                planTopicResponseModel.setUnfulfilledAdmin(Boolean.TRUE);
            }
            planTopicResponseModel.setStudentUsername(planTopicEntity.getStudentUsername());
            planTopicResponseModel.setThemeId(planTopicEntity.getThemeEntity().getIdentifier());
            planTopicResponseModel.setBobNumber(planTopicEntity.getBobEntity().getNumber());
            planTopicResponseModel.setBobName(planTopicEntity.getBobEntity().getName());
            list.add(planTopicResponseModel);
        });
        return list;
    }

    @Override
    public ApiResponse delete(PlanTopicId planTopicId) {
        Optional<PlanTopicEntity> optionalPlanTopic = planTopicRepository.findById(planTopicId.getPlanTopicId());
        if (!optionalPlanTopic.isPresent()) {
            return PLAN_TOPIC_NOT_FOUND;
        }
        if (optionalPlanTopic.get().getActive()) {
            return NOT_DELETED;
        }
        if (!optionalPlanTopic.get().getActive()) {
            planTopicRepository.deleteById(planTopicId.getPlanTopicId());
            return SUCCESS;
        }
        return ERROR;
    }

    //    Admin panel uchun
    @Override
    public List<PlanTopicResponseModel> getPlanTopicListAdmin(String studentUsername) {
        List<PlanTopicEntity> topicEntityList = planTopicRepository.findByStudentUsernameOrderByCreateDateAsc(studentUsername);
        List<PlanTopicResponseModel> list = new ArrayList<>();
        topicEntityList.forEach(planTopicEntity -> {
            PlanTopicResponseModel planTopicResponseModel = new PlanTopicResponseModel();
            planTopicResponseModel.setIdentifier(planTopicEntity.getIdentifier());
            planTopicResponseModel.setName(planTopicEntity.getName());
            planTopicResponseModel.setStartDate(planTopicEntity.getStartDate());
            planTopicResponseModel.setEndDate(planTopicEntity.getEndDate());
            List<FileEntity> planTopicFiles = fileRepository.findAllByPlanTopicId(planTopicEntity.getIdentifier());
            if (planTopicEntity.getEndDate().isBefore(LocalDate.now()) && !(planTopicFiles.size() > 0)) {
                planTopicResponseModel.setUnfulfilledAdmin(Boolean.TRUE);
            }
            planTopicResponseModel.setStudentUsername(planTopicEntity.getStudentUsername());
            planTopicResponseModel.setThemeId(planTopicEntity.getThemeEntity().getIdentifier());
            list.add(planTopicResponseModel);
        });
        return list;
    }

    // PDF

    @Override
    public PDFResponseModel pdf(String studentUsername) {
        PDFResponseModel pdfResponseModel = new PDFResponseModel();
        Optional<StudentEntity> student = studentRepository.findByUsername(studentUsername);
        if (!student.isPresent()) {
            return null;
        }
        StudentEntity st = student.get();
        pdfResponseModel.setTalaba(st.getFirstname() + " " + st.getLastname() + " " + st.getMidname());
        pdfResponseModel.setYunalish(st.getYunalishEntity().getName());
        pdfResponseModel.setKafedra(st.getKafedraEntity().getName());
        Optional<ThemeEntity> theme = themeRepository.findByStudentUsername(studentUsername);
        if (theme.isPresent()) {
            pdfResponseModel.setMavzusi(theme.get().getName());
            pdfResponseModel.setMavzuningDolzarbligi(theme.get().getActual());
            pdfResponseModel.setOquvYili(theme.get().getAcademicYear());
            pdfResponseModel.setDate(theme.get().getAcademicYear());
            for (TeacherEntity teacherEntity : teacherRepository.findAll()) {
                if (teacherEntity.getUsername().equals(theme.get().getTeacherUsername())) {
                    pdfResponseModel.setIlmiyRahbarDarajasi(teacherEntity.getUnvoni());
                }
                if (teacherEntity.getPosition().equals(Position.MAGISTRATURA_BULIM_BOSHLIQ)) {
                    pdfResponseModel.setMagistrBulimBoshliq(
                            teacherEntity.getFirstname() + " " + teacherEntity.getLastname() + " " + teacherEntity.getMidname());
                }
                if (teacherEntity.getPosition().equals(Position.OQUV_ISHLARI_PROREKTOR)) {
                    pdfResponseModel.setOquvIshlarProrektor(
                            teacherEntity.getFirstname() + " " + teacherEntity.getLastname() + " " + teacherEntity.getMidname());
                }
                pdfResponseModel.setKafedraMudiri(st.getKafedraEntity().getKafedraMudiri().getFirstname() + " " + st.getKafedraEntity().getKafedraMudiri().getLastname()
                        + " " + st.getKafedraEntity().getKafedraMudiri().getMidname());
//                if (teacherEntity.getPosition().equals(Position.KAFEDRA_MUDIRI) && teacherEntity.getKafedra().equals(st.getKafedra())) {
//                pdfResponseModel.setKafedraMudiri(teacherEntity.getFirstname() + " " + teacherEntity.getLastname() + " " + teacherEntity.getMidname());
//                }
                if (teacherEntity.getUsername().equals(theme.get().getTeacherUsername())) {
                    pdfResponseModel.setIlmiyRahbar(
                            teacherEntity.getFirstname() + " " + teacherEntity.getLastname() + " " + teacherEntity.getMidname());
                }
            }
        }
        return pdfResponseModel;
    }
}
