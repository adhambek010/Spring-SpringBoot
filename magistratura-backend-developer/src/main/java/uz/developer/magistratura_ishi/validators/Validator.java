package uz.developer.magistratura_ishi.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.developer.magistratura_ishi.entity.FakultetEntity;
import uz.developer.magistratura_ishi.entity.KafedraEntity;
import uz.developer.magistratura_ishi.entity.YunalishEntity;
import uz.developer.magistratura_ishi.model.university.FakultetReceiveModel;
import uz.developer.magistratura_ishi.model.university.KafedraReceiveModel;
import uz.developer.magistratura_ishi.model.university.YunalishReceiveModel;
import uz.developer.magistratura_ishi.repository.FakultetRepository;
import uz.developer.magistratura_ishi.repository.KafedraRepository;
import uz.developer.magistratura_ishi.repository.YunalishRepository;
import uz.developer.magistratura_ishi.validators.dataClass.ConstraintError;
import uz.developer.magistratura_ishi.validators.dataClass.ValidatorError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class Validator {

    @Autowired
    private FakultetRepository fakultetRepository;

    @Autowired
    private YunalishRepository yunalishRepository;

    @Autowired
    private KafedraRepository kafedraRepository;


    public ValidatorError validateAddFakultet(FakultetReceiveModel fakultetReceiveModel) {

        Optional<FakultetEntity> optional = fakultetRepository.findByName(fakultetReceiveModel.getName());
        List<ConstraintError> constraintErrors = new ArrayList<>();

        if (optional.isPresent()) {
            constraintErrors.add(new ConstraintError("Fakuttet nomi", "", "Bu fakultet allaqachon mavjud."));
        }

        if (fakultetReceiveModel.getName().isEmpty()) {
            constraintErrors.add(new ConstraintError("Fakuttet nomi", "", "Fakultet nomi bo'sh bo'lmasligi kerak."));
        }
        if (fakultetReceiveModel.getDekan().isEmpty()) {
            constraintErrors.add(new ConstraintError("Fakuttet dekani", "", "Fakultet nomi bo'sh bo'lmasligi kerak."));
        }
        if (!constraintErrors.isEmpty()) {
            return new ValidatorError("Fakultet add error!", constraintErrors);
        }
        return new ValidatorError("Success!", constraintErrors);
    }

    public ValidatorError validateAddYunalish(YunalishReceiveModel yunalishReceiveModel) {
        Optional<YunalishEntity> optional = yunalishRepository.findByName(yunalishReceiveModel.getName());
        List<ConstraintError> constraintErrors = new ArrayList<>();
        if (optional.isPresent()) {
            constraintErrors.add(new ConstraintError("Yunalish nomi", "", "Bu yunalish allaqachon mavjud."));
        }
        if (yunalishReceiveModel.getName().isEmpty()) {
            constraintErrors.add(new ConstraintError("Yunalish nomi", "", "Yunalish nomi bo'sh bo'lmasligi kerak."));
        }
        if (yunalishReceiveModel.getFakultet().isEmpty()) {
            constraintErrors.add(new ConstraintError("Fakultet", "", "Fakultet bo'sh bo'lmasligi kerak."));
        }

        if (!constraintErrors.isEmpty()) {
            return new ValidatorError("Yunalish add error!", constraintErrors);
        }

        return new ValidatorError("Success!", constraintErrors);
    }

    public ValidatorError validateAddKafedra(KafedraReceiveModel kafedraReceiveModel) {
        Optional<KafedraEntity> optional = kafedraRepository.findByName(kafedraReceiveModel.getName());
        List<ConstraintError> constraintErrors = new ArrayList<>();

        if (optional.isPresent()) {
            constraintErrors.add(new ConstraintError("Kafedra nomi", "", "Bu kafera allaqachon mavjud."));
        }
        if (kafedraReceiveModel.getName().isEmpty()) {
            constraintErrors.add(new ConstraintError("Kafedra nomi", "", "Kafedra nomi bo'sh bo'lmasligi kerak."));
        }
        if (kafedraReceiveModel.getKafedraMudiri().isEmpty()) {
            constraintErrors.add(new ConstraintError("Kafedra mudiri", "", "Kafedra mudiri bo'sh bo'lmasligi kerak."));
        }
        if (kafedraReceiveModel.getFakultet().isEmpty()) {
            constraintErrors.add(new ConstraintError("Fakultet", "", "Fakultet bo'sh bo'lmasligi kerak."));
        }
        if (!constraintErrors.isEmpty()) {
            return new ValidatorError("Kafedra add error!", constraintErrors);
        }

        return new ValidatorError("Success!", constraintErrors);
    }
}
