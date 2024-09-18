package uz.developer.magistratura_ishi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uz.developer.magistratura_ishi.entity.MaqolaEntity;
import uz.developer.magistratura_ishi.entity.StudentEntity;
import uz.developer.magistratura_ishi.model.receive.MaqolaDTO;
import uz.developer.magistratura_ishi.model.response.MaqolaResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;
import uz.developer.magistratura_ishi.repository.MaqolaRepository;
import uz.developer.magistratura_ishi.repository.StudentRepository;
import uz.developer.magistratura_ishi.service.MaqolaService;
import uz.developer.magistratura_ishi.service.base.BaseService;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class MaqolaServiceImp implements MaqolaService, BaseService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MaqolaRepository maqolaRepository;

    @Override
    public ApiResponse addMaqola(MaqolaDTO maqolaDTO) {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findByUsername(maqolaDTO.getUsername());
        if (!optionalStudentEntity.isPresent()) {
            log.info("################ Student not found! Username: " + maqolaDTO.getUsername() + " maqolaDTO: " + maqolaDTO + " ##################");
            return USER_NOT_FOUND;
        }

        MaqolaEntity maqolaEntity = new MaqolaEntity();
        maqolaEntity.setIdentifier(UUID.randomUUID().toString());
        maqolaEntity.setName(maqolaDTO.getName());
        maqolaEntity.setAuthor(maqolaDTO.getAuthor());
        maqolaEntity.setCreateDate(LocalDateTime.now());
        maqolaEntity.setStudentEntity(optionalStudentEntity.get());
        maqolaRepository.save(maqolaEntity);

        optionalStudentEntity.get().getMaqolaEntities().add(maqolaEntity);
        studentRepository.save(optionalStudentEntity.get());
        log.info("---------------- addMaqola studentUsername: " + maqolaDTO.getUsername() + " ---------------------");
        return SUCCESS;
    }

    @Override
    public List<MaqolaResponseModel> getMaqolaList(String studentUsername) {
        log.info("---------------- student: " + studentUsername + " getMaqolaList---------------------");
        Optional<StudentEntity> optionalStudent = studentRepository.findByUsername(studentUsername);
        List<MaqolaEntity> allByStudentEntity = maqolaRepository.findAllByStudentEntity(optionalStudent.get());
        List<MaqolaResponseModel> list = new ArrayList<>();
        allByStudentEntity.forEach(maqolaEntity -> {
            MaqolaResponseModel maqolaResponseModel = new MaqolaResponseModel();
            maqolaResponseModel.setIdentifier(maqolaEntity.getIdentifier());
            maqolaResponseModel.setName(maqolaEntity.getName());
            maqolaResponseModel.setAuthor(maqolaEntity.getAuthor());
            maqolaResponseModel.setFile(maqolaEntity.getFile());
            maqolaResponseModel.setUsername(optionalStudent.get().getUsername());
            list.add(maqolaResponseModel);
        });
        return list;
    }

    @Override
    public ApiResponse delete(String maqolaId) {
        Optional<MaqolaEntity> optionalMaqolaEntity = maqolaRepository.findById(maqolaId);
        if (!optionalMaqolaEntity.isPresent()) {
            return NOT_FOUND;
            }
        if (!StringUtils.hasText(optionalMaqolaEntity.get().getFile()))
            maqolaRepository.deleteById(maqolaId);
        log.info("---------------- Delete maqolaId: " + maqolaId + " ---------------------");
        return SUCCESS;
    }

    @Override
    public MaqolaResponseModel getMaqolaById(String maqolaId) {
        Optional<MaqolaEntity> optionalMaqolaEntity = maqolaRepository.findById(maqolaId);
        MaqolaResponseModel maqolaResponseModel = new MaqolaResponseModel();
        optionalMaqolaEntity.ifPresent(maqolaEntity -> {
            maqolaResponseModel.setIdentifier(maqolaEntity.getIdentifier());
            maqolaResponseModel.setName(maqolaEntity.getName());
            maqolaResponseModel.setAuthor(maqolaEntity.getAuthor());
            maqolaResponseModel.setFile(maqolaEntity.getFile());
            maqolaResponseModel.setUsername(maqolaEntity.getStudentEntity().getUsername());
        });
        return maqolaResponseModel;
    }

    @Override
    public ApiResponse editMaqola(MaqolaDTO maqolaDTO) {
        Optional<MaqolaEntity> optionalMaqolaEntity = maqolaRepository.findById(maqolaDTO.getMaqolaId());
        if (!optionalMaqolaEntity.isPresent())
            return NOT_FOUND;

        MaqolaEntity maqolaEntity = optionalMaqolaEntity.get();
        log.info("************** Student username: " + maqolaDTO.getUsername() + " [Edit maqola]: "+ maqolaEntity + " to " + maqolaDTO + " ****************");
        if (!StringUtils.hasText(maqolaEntity.getFile())) {
            maqolaEntity.setName(maqolaDTO.getName());
            maqolaEntity.setAuthor(maqolaDTO.getAuthor());
            maqolaEntity.setLastUpdateTime(LocalDateTime.now());
            maqolaRepository.save(maqolaEntity);
            return SUCCESS;
        } else {
            return CANNOT_BE_CHANGED;
        }
    }
}
