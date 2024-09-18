package uz.developer.magistratura_ishi.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uz.developer.magistratura_ishi.entity.*;
import uz.developer.magistratura_ishi.model.receive.*;
import uz.developer.magistratura_ishi.repository.*;
import uz.developer.magistratura_ishi.validators.dataClass.ConstraintError;
import uz.developer.magistratura_ishi.validators.dataClass.EmailOrPhoneValidator;
import uz.developer.magistratura_ishi.validators.dataClass.ValidatorError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class UserValidators {

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
    private MaqolaRepository maqolaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public ValidatorError validateUserPasswordChange(ChangePasswordDTO changePasswordDTO) {
        List<ConstraintError> constraintErrors = new ArrayList<>();

        if (!StringUtils.isEmpty(changePasswordDTO.getNewPassword()) && !(changePasswordDTO.getNewPassword().length() > 5 && changePasswordDTO.getNewPassword().length() < 20)) {
            constraintErrors.add(new ConstraintError("password", "", "Password length must be at least 6 characters long"));
        }
        if (StringUtils.isEmpty(changePasswordDTO.getNewPassword())) {
            constraintErrors.add(new ConstraintError("NewPassword", "", "NewPassword cannot be empty"));
        }
        Optional<StudentEntity> usersEntityOptional = studentRepository.findById(changePasswordDTO.getStudentUsername());
        if (usersEntityOptional.isPresent() && !passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), usersEntityOptional.get().getPassword())) {
            constraintErrors.add(new ConstraintError("Password", "", "User's current password is not correct!"));
        }
        log.info("### password matches " + passwordEncoder.matches(changePasswordDTO.getNewPassword(), usersEntityOptional.get().getPassword()) + " ####");
        if (usersEntityOptional.isPresent() && passwordEncoder.matches(changePasswordDTO.getNewPassword(), usersEntityOptional.get().getPassword())) {
            constraintErrors.add(new ConstraintError("newPassword", "", "New password must differ from the old one!"));
        }

        if (!constraintErrors.isEmpty()) {
            return new ValidatorError("User password change error!", constraintErrors);
        }
        return new ValidatorError("Success!", constraintErrors);
    }


    public ValidatorError validateUpdatePersonalInfo(UpdateDTO updateDTO) {
        Optional<StudentEntity> optional = studentRepository.findById(updateDTO.getUserId());
        List<ConstraintError> constraintErrors = new ArrayList<>();

        if (!updateDTO.getPhoneNumber().isEmpty() && !EmailOrPhoneValidator.isPhoneFormat(updateDTO.getPhoneNumber())) {
            constraintErrors.add(new ConstraintError("phone", "", "Hmm. That phone number isn't valid"));
        }
        if (Objects.nonNull(updateDTO.getEmail()) && !EmailOrPhoneValidator.isEmailFormat(updateDTO.getEmail())) {
            constraintErrors.add(new ConstraintError("email", "", "Hmm. That email address isn't valid"));
        }

        if (!updateDTO.getPhoneNumber().isEmpty()) {
            updateDTO.setPhoneNumber(updateDTO.getPhoneNumber().replace("+", ""));
        }

        if (!constraintErrors.isEmpty()) {
            return new ValidatorError("User update error!", constraintErrors);
        }
        return new ValidatorError("Success!", constraintErrors);
    }

    public ValidatorError validatePlanTopic(PlanTopicDTO planTopicDTO) {
        Optional<PlanTopicEntity> optional = planTopicRepository.findByName(planTopicDTO.getName());
        List<ConstraintError> constraintErrors = new ArrayList<>();
        if (optional.isPresent()) {
            constraintErrors.add(new ConstraintError("PlanTopicName", "", "This Plan Topic already exist"));
        }
        if (Objects.isNull(planTopicDTO.getName())) {
            constraintErrors.add(new ConstraintError("Plan topic name", "", "PlanTopic cannot be empty"));
        }
        if (Objects.isNull(planTopicDTO.getStartDate())) {
            constraintErrors.add(new ConstraintError("startDate", "", "startDate cannot be empty"));
        }
        if (Objects.isNull(planTopicDTO.getEndDate())) {
            constraintErrors.add(new ConstraintError("endDate", "", "endDate cannot be empty"));
        }
//        if (Objects.isNull(planTopicDTO.getBobName())) {
//            constraintErrors.add(new ConstraintError("bobName", "", "bobName cannot be empty"));
//        }
        if (!constraintErrors.isEmpty()) {
            return new ValidatorError("Plan topic add error!", constraintErrors);
        }
        return new ValidatorError("Success!", constraintErrors);
    }

    public ValidatorError validateAddStudent(StudentReceiveModel studentReceiveModel) {
        List<ConstraintError> constraintErrors = new ArrayList<>();
        Optional<StudentEntity> optionalStudent
                = studentRepository.findByUsername(studentReceiveModel.getUsername());
        if (optionalStudent.isPresent()) {
            constraintErrors.add(new ConstraintError("studentUsername", "", "Bu username allaqachon mavjud"));
        }
        if (studentReceiveModel.getFirstname().isEmpty()) {
            constraintErrors.add(new ConstraintError("firstname", "", "firstname cannot be empty"));
        }
        if (studentReceiveModel.getLastname().isEmpty()) {
            constraintErrors.add(new ConstraintError("lastname", "", "lastname cannot be empty"));
        }
        if (studentReceiveModel.getMidname().isEmpty()) {
            constraintErrors.add(new ConstraintError("midname", "", "midname cannot be empty"));
        }
        if (studentReceiveModel.getUsername().isEmpty() || !studentReceiveModel.getUsername().startsWith("student")) {
            constraintErrors.add(new ConstraintError("username", "", "Username `student` so'zi biln boshlanishi kerek " +
                    "va bo'sh bo'lmasligi kerak"));
        }
        if (studentReceiveModel.getPassword().isEmpty() || studentReceiveModel.getPassword().length() < 5) {
            constraintErrors.add(new ConstraintError("password", "", "password cannot be empty and password length >= 5"));
        }
        if (studentReceiveModel.getKafedra().isEmpty()) {
            constraintErrors.add(new ConstraintError("kafedra", "", "kafedra cannot be empty"));
        }
        if (studentReceiveModel.getYunalish().isEmpty()) {
            constraintErrors.add(new ConstraintError("yunalish", "", "yunalish cannot be empty"));
        }
        if (studentReceiveModel.getStudentGroup().isEmpty()) {
            constraintErrors.add(new ConstraintError("studentGroup", "", "studentGroup cannot be empty"));
        }
        if (!constraintErrors.isEmpty()) {
            return new ValidatorError("Student add error!", constraintErrors);
        }
        return new ValidatorError("Success!", constraintErrors);
    }

    public ValidatorError validateAddTeacher(TeacherReceiveModel teacherReceiveModel) {
        List<ConstraintError> constraintErrors = new ArrayList<>();
        Optional<TeacherEntity> optional
                = teacherRepository.findByUsername(teacherReceiveModel.getUsername());
        if (optional.isPresent()) {
            constraintErrors.add(new ConstraintError("TeacherUsername", "", "Bu username allaqachon mavjud"));
        }
        if (teacherReceiveModel.getFirstname().isEmpty()) {
            constraintErrors.add(new ConstraintError("Firstname", "", "firstname cannot be empty"));
        }
        if (teacherReceiveModel.getLastname().isEmpty()) {
            constraintErrors.add(new ConstraintError("Lastname", "", "lastname cannot be empty"));
        }
        if (teacherReceiveModel.getMidname().isEmpty()) {
            constraintErrors.add(new ConstraintError("Midname", "", "midname cannot be empty"));
        }
        if (teacherReceiveModel.getUsername().isEmpty()) {
            constraintErrors.add(new ConstraintError("Username", "", "Username bo'sh bo'lmasligi kerak"));
        }
        if (teacherReceiveModel.getPassword().isEmpty() || teacherReceiveModel.getPassword().length() < 5) {
            constraintErrors.add(new ConstraintError("Password", "", "password cannot be empty and password length >= 5"));
        }
        if (teacherReceiveModel.getUnvoni().isEmpty()) {
            constraintErrors.add(new ConstraintError("Unvoni", "", "Unvoni cannot be empty"));
        }
        if (teacherReceiveModel.getPosition().isEmpty()) {
            constraintErrors.add(new ConstraintError("Position", "", "Position cannot be empty"));
        }
        if (!constraintErrors.isEmpty()) {
            return new ValidatorError("Student add error!", constraintErrors);
        }
        return new ValidatorError("Success!", constraintErrors);
    }

    public ValidatorError validateAddBob(BobDTO bobDTO) {
        List<ConstraintError> constraintErrors = new ArrayList<>();
        Optional<BobEntity> optionalBobEntity = bobRepository.findByNameOrNumber(bobDTO.getName(), bobDTO.getNumber());
        if (optionalBobEntity.isPresent()) {
            constraintErrors.add(new ConstraintError("bob", "", "Bu bob allaqachon mavjud"));
        }
        if (Objects.isNull(bobDTO.getName()) || bobDTO.getName().isEmpty()) {
            constraintErrors.add(new ConstraintError("Bob nomi", "", "Bob nomi bo'sh bo'lmasligi kerak"));
        }
        if (Objects.isNull(bobDTO.getNumber()) || bobDTO.getNumber() <= 0) {
            constraintErrors.add(new ConstraintError("Bob nomeri", "", "Bob nomeri bo'sh bo'lmasligi va 0 dan katta bo'lishi kerak"));
        }
        if (!constraintErrors.isEmpty()) {
            return new ValidatorError("Bob add error!", constraintErrors);
        }
        return new ValidatorError("Success!", constraintErrors);
    }

    public ValidatorError validateAddMaqola(MaqolaDTO maqolaDTO) {
        List<ConstraintError> constraintErrors = new ArrayList<>();
        Optional<MaqolaEntity> optionalMaqolaEntity = maqolaRepository.findByName(maqolaDTO.getName());
        if (optionalMaqolaEntity.isPresent()) {
            constraintErrors.add(new ConstraintError("Maqola", "", "Bu Maqola allaqachon mavjud"));
        }
        if (Objects.isNull(maqolaDTO.getName()) || maqolaDTO.getName().isEmpty()) {
            constraintErrors.add(new ConstraintError("Maqola nomi", "", "Maqola nomi bo'sh bo'lmasligi kerak"));
        }
        if (Objects.isNull(maqolaDTO.getAuthor()) || maqolaDTO.getAuthor().equals("")) {
            constraintErrors.add(new ConstraintError("Maqola author", "", "Maqola authori bo'sh bo'lmasligi kerak"));
        }
        if (!constraintErrors.isEmpty()) {
            return new ValidatorError("Maqola add error!", constraintErrors);
        }
        return new ValidatorError("Success!", constraintErrors);
    }
}

