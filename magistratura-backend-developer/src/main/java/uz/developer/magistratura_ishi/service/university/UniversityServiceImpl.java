package uz.developer.magistratura_ishi.service.university;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.entity.FakultetEntity;
import uz.developer.magistratura_ishi.entity.KafedraEntity;
import uz.developer.magistratura_ishi.entity.TeacherEntity;
import uz.developer.magistratura_ishi.entity.YunalishEntity;
import uz.developer.magistratura_ishi.entity.base.Position;
import uz.developer.magistratura_ishi.model.response.TeacherResponseModel;
import uz.developer.magistratura_ishi.model.response.base.ApiResponse;
import uz.developer.magistratura_ishi.model.university.*;
import uz.developer.magistratura_ishi.repository.FakultetRepository;
import uz.developer.magistratura_ishi.repository.KafedraRepository;
import uz.developer.magistratura_ishi.repository.TeacherRepository;
import uz.developer.magistratura_ishi.repository.YunalishRepository;
import uz.developer.magistratura_ishi.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static uz.developer.magistratura_ishi.service.impl.TeacherServiceImp.getTeacherResponseModels;

@Slf4j
@Service
public class UniversityServiceImpl implements UniversityService, BaseService {

    @Autowired
    private FakultetRepository fakultetRepository;

    @Autowired
    private YunalishRepository yunalishRepository;

    @Autowired
    private KafedraRepository kafedraRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    public ApiResponse addFakulty(FakultetReceiveModel fakultetReceiveModel) {
        FakultetEntity fakultetEntity = new FakultetEntity();
        fakultetEntity.setIdentifier(UUID.randomUUID().toString());
        fakultetEntity.setName(fakultetReceiveModel.getName());
        Optional<TeacherEntity> dekan = teacherRepository.findByUsername(fakultetReceiveModel.getDekan());
        dekan.ifPresent(fakultetEntity::setDekan);
        fakultetRepository.save(fakultetEntity);
        return SUCCESS;
    }

    public ApiResponse addYunalish(YunalishReceiveModel yunalishReceiveModel) {
        YunalishEntity yunalishEntity = new YunalishEntity();
        yunalishEntity.setIdentifier(UUID.randomUUID().toString());
        yunalishEntity.setName(yunalishReceiveModel.getName());
        Optional<FakultetEntity> optionalFakultetEntity
                = fakultetRepository.findById(yunalishReceiveModel.getFakultet());
        if (optionalFakultetEntity.isPresent()) {
            yunalishEntity.setFakultetId(yunalishReceiveModel.getFakultet());
        }
        yunalishRepository.save(yunalishEntity);
        return SUCCESS;
    }

    public Object addKafedra(KafedraReceiveModel kafedraReceiveModel) {
        KafedraEntity kafedraEntity = new KafedraEntity();
        kafedraEntity.setIdentifier(UUID.randomUUID().toString());
        kafedraEntity.setName(kafedraReceiveModel.getName());
        Optional<TeacherEntity> optionalTeacher = teacherRepository.findByUsername(kafedraReceiveModel.getKafedraMudiri());
        optionalTeacher.ifPresent(kafedraEntity::setKafedraMudiri);
        Optional<FakultetEntity> optionalFakultet = fakultetRepository.findById(kafedraReceiveModel.getFakultet());
        if (optionalFakultet.isPresent()) {
            kafedraEntity.setFakultetId(kafedraReceiveModel.getFakultet());
        }
        kafedraRepository.save(kafedraEntity);
        return SUCCESS;
    }

    public List<FakultetResponseModel> getFakultetList() {
        List<FakultetResponseModel> responseList = new ArrayList<>();
        List<FakultetEntity> all = fakultetRepository.findAll();
        all.forEach(fakultetEntity -> {
            FakultetResponseModel fakultetResponseModel = new FakultetResponseModel();
            fakultetResponseModel.setId(fakultetEntity.getIdentifier());
            fakultetResponseModel.setName(fakultetEntity.getName());
            fakultetResponseModel.setDekan(
                    fakultetEntity.getDekan().getFirstname() + " "
                            + fakultetEntity.getDekan().getLastname() + " " + fakultetEntity.getDekan().getMidname());
            responseList.add(fakultetResponseModel);
        });
        return responseList;
    }

    public List<TeacherResponseModel> getFakultetDekanList() {
        return getTeacherResponseModels(teacherRepository);
    }

    public List<TeacherResponseModel> getKafedraMudiriList() {
        List<TeacherEntity> repositoryAll = teacherRepository.findAll();
        List<TeacherResponseModel> list = new ArrayList<>();
        repositoryAll.forEach(entity -> {
            TeacherResponseModel model = new TeacherResponseModel();
            if (entity.getPosition().equals(Position.KAFEDRA_MUDIRI)) {
                model.setUserId(entity.getIdentifier());
                model.setPosition(entity.getPosition().name());
                BeanUtils.copyProperties(entity, model);
                list.add(model);
            }
        });
        return list;
    }

    public List<YunalishResponseModel> getYunalishList() {
        List<YunalishResponseModel> list = new ArrayList<>();
        List<YunalishEntity> all = yunalishRepository.findAll();
        all.forEach(yunalishEntity -> {
            YunalishResponseModel yunalishResponseModel = new YunalishResponseModel();
            yunalishResponseModel.setId(yunalishEntity.getIdentifier());
            yunalishResponseModel.setName(yunalishEntity.getName());

            yunalishResponseModel.setFakultet(yunalishEntity.getFakultetEntity().getName());
            list.add(yunalishResponseModel);
        });
        return list;
    }

    public List<KafedraResponseModel> getKafedraList() {
        List<KafedraResponseModel> list = new ArrayList<>();
        List<KafedraEntity> all = kafedraRepository.findAll();
        all.forEach(kafedraEntity -> {
            KafedraResponseModel kafedraResponseModel = new KafedraResponseModel();
            kafedraResponseModel.setId(kafedraEntity.getIdentifier());
            kafedraResponseModel.setName(kafedraEntity.getName());
            kafedraResponseModel.setFakultet(kafedraEntity.getFakultetEntity().getName());
            kafedraResponseModel.setKafedraMudiri(kafedraEntity.getKafedraMudiri().getFirstname() + " "
                    + kafedraEntity.getKafedraMudiri().getLastname() + " "
                    + kafedraEntity.getKafedraMudiri().getMidname());
            list.add(kafedraResponseModel);
        });
        return list;
    }

    @Override
    public FakultetResponseModel getFakultet(String fakultetId) {
        FakultetResponseModel fakultetResponseModel = new FakultetResponseModel();
        Optional<FakultetEntity> optionalFakultet = fakultetRepository.findById(fakultetId);
        if (optionalFakultet.isPresent()) {
            fakultetResponseModel.setId(optionalFakultet.get().getIdentifier());
            fakultetResponseModel.setName(optionalFakultet.get().getName());
            fakultetResponseModel.setDekan(optionalFakultet.get().getDekan().getUsername());
        }
        return fakultetResponseModel;
    }

    @Override
    public YunalishResponseModel getYunalish(String yunalishId) {

        YunalishResponseModel yunalishResponseModel = new YunalishResponseModel();
        Optional<YunalishEntity> optionalYunalish = yunalishRepository.findById(yunalishId);
        if (optionalYunalish.isPresent()) {
            yunalishResponseModel.setId(optionalYunalish.get().getIdentifier());
            yunalishResponseModel.setName(optionalYunalish.get().getName());
            yunalishResponseModel.setFakultet(optionalYunalish.get().getFakultetEntity().getName());
        }
        return yunalishResponseModel;
    }

    @Override
    public KafedraResponseModel getKafedra(String kafedraId) {
        KafedraResponseModel kafedraResponseModel = new KafedraResponseModel();
        Optional<KafedraEntity> optionalKafedra = kafedraRepository.findById(kafedraId);
        if (optionalKafedra.isPresent()) {
            kafedraResponseModel.setId(optionalKafedra.get().getIdentifier());
            kafedraResponseModel.setName(optionalKafedra.get().getName());
            kafedraResponseModel.setFakultet(optionalKafedra.get().getFakultetEntity().getName());
            kafedraResponseModel.setKafedraMudiri(optionalKafedra.get().getKafedraMudiri().getUsername());
        }
        return kafedraResponseModel;
    }

    @Override
    public ApiResponse editFakultet(FakultetReceiveModel fakultetReceiveModel) {
        Optional<FakultetEntity> optionalFakultetEntity
                = fakultetRepository.findById(fakultetReceiveModel.getIdentifier());
        if (!optionalFakultetEntity.isPresent()) {
            return NOT_FOUND;
        }
        FakultetEntity fakultetEntity = optionalFakultetEntity.get();
        fakultetEntity.setName(fakultetReceiveModel.getName());
        Optional<TeacherEntity> optionalTeacher
                = teacherRepository.findByUsername(fakultetReceiveModel.getDekan());
        if (!optionalTeacher.isPresent() || !optionalTeacher.get().getPosition().equals(Position.DEKAN)) {
            return NOT_FOUND;
        }
        fakultetEntity.setDekan(optionalTeacher.get());
        fakultetRepository.save(fakultetEntity);
        return SUCCESS;
    }

    @Override
    public ApiResponse editYunalish(YunalishReceiveModel yunalishReceiveModel) {
        return null;
    }
}
