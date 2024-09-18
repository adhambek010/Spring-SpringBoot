package uz.developer.magistratura_ishi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.entity.BobEntity;
import uz.developer.magistratura_ishi.entity.StudentEntity;
import uz.developer.magistratura_ishi.model.receive.BobDTO;
import uz.developer.magistratura_ishi.model.response.BobResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;
import uz.developer.magistratura_ishi.repository.BobRepository;
import uz.developer.magistratura_ishi.repository.PlanTopicRepository;
import uz.developer.magistratura_ishi.repository.StudentRepository;
import uz.developer.magistratura_ishi.service.BobService;
import uz.developer.magistratura_ishi.service.base.BaseService;
import uz.developer.magistratura_ishi.service.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class BobServiceImpl implements BobService, BaseService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BobRepository bobRepository;

    @Autowired
    private PlanTopicRepository planTopicRepository;

    @Override
    public ApiResponse addBob(BobDTO bobDTO) {
        Optional<StudentEntity> optionalStudent = studentRepository.findByUsername(bobDTO.getUsername());
        if (!optionalStudent.isPresent()) {
            log.info("############################# Student not found! Student username: " + bobDTO.getUsername() + " #####################################");
            return USER_NOT_FOUND;
        }
        BobEntity bobEntity = new BobEntity();
        bobEntity.setIdentifier(UUID.randomUUID().toString());
        bobEntity.setNumber(bobDTO.getNumber());
        bobEntity.setName(bobDTO.getName());
        bobEntity.setCreateDate(LocalDateTime.now());
        bobEntity.setStudentEntity(optionalStudent.get());
        bobRepository.save(bobEntity);

        optionalStudent.get().getBobEntities().add(bobEntity);
        studentRepository.save(optionalStudent.get());
        return SUCCESS;
    }

    @Override
    public List<BobResponseModel> getBobList(String studentUsername) {
        log.info("---------------- student: " + studentUsername + " getBobList---------------------");
        Optional<StudentEntity> optionalStudent = studentRepository.findByUsername(studentUsername);
        if (!optionalStudent.isPresent()) {
            log.info("$$$$$$$$$$$$$$$$$ student not found $$$$$$$$$$$$$$$$$");
            throw new NotFoundException(studentUsername + " bu user topilmadi!");
        }

        List<BobEntity> allByStudentEntity = bobRepository.findAllByStudentEntityOrderByNumber(optionalStudent.get());
        List<BobResponseModel> list = new ArrayList<>();
        allByStudentEntity.forEach(bobEntity -> {
            BobResponseModel bobResponseModel = new BobResponseModel();
            bobResponseModel.setIdentifier(bobEntity.getIdentifier());
            bobResponseModel.setName(bobEntity.getName());
            bobResponseModel.setNumber(bobEntity.getNumber());
            bobResponseModel.setXulosa(bobEntity.getXulosa());
            list.add(bobResponseModel);
        });
        return list;
    }

    @Override
    public ApiResponse editBob(BobDTO bobDTO) {
        Optional<BobEntity> optionalBob = bobRepository.findById(bobDTO.getBobId());
        if (optionalBob.isPresent()){
            BobEntity bobEntity = optionalBob.get();
            bobEntity.setName(bobDTO.getName());
            bobRepository.save(bobEntity);
            return SUCCESS;
        }
        return NOT_FOUND;
    }

    @Override
    public BobDTO getById(String bobId) {
        Optional<BobEntity> optionalBob = bobRepository.findById(bobId);
        if (!optionalBob.isPresent()) {
            return null;
        }
        BobDTO bobDTO = new BobDTO();
        bobDTO.setBobId(optionalBob.get().getIdentifier());
        bobDTO.setName(optionalBob.get().getName());
        bobDTO.setNumber(optionalBob.get().getNumber());
        return bobDTO;
    }

    @Override
    public ApiResponse delete(String bobId) {
        Optional<BobEntity> optionalBob = bobRepository.findById(bobId);
        if (!optionalBob.isPresent()) {
            return NOT_FOUND;
        }
        if (optionalBob.get().getPlanTopicEntities().size() > 0) {
            log.info("--------------------------------- Not Delete bobId:" + bobId + " ---------------------------");
            return ERROR;
        } else {
            log.info("--------------------------------- Delete BobEntity: " + optionalBob.get() + " ---------------------------");
            bobRepository.delete(optionalBob.get());
        }
        return SUCCESS;
    }
}
