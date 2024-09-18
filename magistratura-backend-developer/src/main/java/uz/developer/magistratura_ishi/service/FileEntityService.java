package uz.developer.magistratura_ishi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.entity.FileEntity;
import uz.developer.magistratura_ishi.entity.MaqolaEntity;
import uz.developer.magistratura_ishi.entity.PlanTopicEntity;
import uz.developer.magistratura_ishi.model.receive.FileReceiveModel;
import uz.developer.magistratura_ishi.model.response.FileResponseModel;
import uz.developer.magistratura_ishi.repository.FileRepository;
import uz.developer.magistratura_ishi.repository.MaqolaRepository;
import uz.developer.magistratura_ishi.repository.PlanTopicRepository;
import uz.developer.magistratura_ishi.service.exception.AlreadyExistException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileEntityService {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    PlanTopicRepository planTopicRepository;

    @Autowired
    MaqolaRepository maqolaRepository;

    public void saveplanTopicFile(FileReceiveModel fileReceiveModel) {
        Optional<PlanTopicEntity> optionalPlanTopic = planTopicRepository.findById(fileReceiveModel.getSubjectFileId());
        if (optionalPlanTopic.isPresent()) {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setIdentifier(UUID.randomUUID().toString());
            fileEntity.setCreateDate(LocalDateTime.now());
            fileEntity.setFileName(fileReceiveModel.getFileName());
            fileEntity.setFileDownloadUri(fileReceiveModel.getFileDownloadUri());
            fileEntity.setFileType(fileReceiveModel.getFileType());
            fileEntity.setSize(fileReceiveModel.getSize());
            fileEntity.setPlanTopicId(fileReceiveModel.getSubjectFileId());
            fileEntity.setUsername(fileReceiveModel.getUsername());
            fileRepository.save(fileEntity);
        }
    }

    public void saveMaqolaFile(FileReceiveModel fileReceiveModel) {
        Optional<MaqolaEntity> optionalMaqolaEntity = maqolaRepository.findById(fileReceiveModel.getSubjectFileId());
        if (optionalMaqolaEntity.isPresent()) {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setIdentifier(UUID.randomUUID().toString());
            fileEntity.setCreateDate(LocalDateTime.now());
            fileEntity.setFileName(fileReceiveModel.getFileName());
            fileEntity.setFileDownloadUri(fileReceiveModel.getFileDownloadUri());
            fileEntity.setFileType(fileReceiveModel.getFileType());
            fileEntity.setSize(fileReceiveModel.getSize());
            fileEntity.setMaqolaId(fileReceiveModel.getSubjectFileId());
            fileEntity.setUsername(fileReceiveModel.getUsername());
            fileRepository.save(fileEntity);
        }
    }

    public List<FileResponseModel> getByTopicId(String plantopicId) {
        List<FileResponseModel> list = new ArrayList<>();
        List<FileEntity> fileEntityList = fileRepository.findAllByPlanTopicId(plantopicId);
        fileEntityList.forEach(fileEntity -> {
            FileResponseModel fileResponseModel = new FileResponseModel();
            fileResponseModel.setFileName(fileEntity.getFileName());
            fileResponseModel.setFileDownloadUri(fileEntity.getFileDownloadUri());
            fileResponseModel.setCreateDate(fileEntity.getCreateDate().toString());
            list.add(fileResponseModel);
        });
        return list;
    }

    public List<FileResponseModel> getByMaqolaId(String maqolaId) {
        List<FileResponseModel> maqolaFileList = new ArrayList<>();
        List<FileEntity> fileEntityList = fileRepository.findByMaqolaId(maqolaId);
        fileEntityList.forEach(fileEntity -> {
            FileResponseModel fileResponseModel = new FileResponseModel();
            fileResponseModel.setFileName(fileEntity.getFileName());
            fileResponseModel.setFileDownloadUri(fileEntity.getFileDownloadUri());
            fileResponseModel.setCreateDate(fileEntity.getCreateDate().toString());
            maqolaFileList.add(fileResponseModel);
        });
        return maqolaFileList;
    }

    public void isThereFileName(String fileName) {
        Optional<FileEntity> thereFileName = fileRepository.findByFileName(fileName);
        if (thereFileName.isPresent()){
            throw  new AlreadyExistException("Bu nomdagi fayl allaqachon mavjud. " +
                    "Iltimos fayl nomini o'zgaririb qaytadan yuklang!");
        }
    }
}
