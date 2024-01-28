package com.jobayour.modules.qualification;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QualificationService {

    private QualificationRepository qualificationRepository;
    private final ModelMapper modelMapper;

    public QualificationService(QualificationRepository qualificationRepository, ModelMapper modelMapper) {
        this.qualificationRepository = qualificationRepository;
        this.modelMapper = modelMapper;
    }

    // 증명리스트 조회
    public List<QualificationDTO> findqualById(String id) {
        List<Qualification> qualList = qualificationRepository.findQualificationByUserId(id);
        List<QualificationDTO> list = qualList.stream()
                .sorted(Comparator.comparing(Qualification::getQualificationsNumber))
                .map(qualification -> modelMapper.map(qualification, QualificationDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    // 증명 추가
    public void addQual(Qualification qualification) {
        Qualification qual = new Qualification();
        qual.setUserId(qualification.getUserId());
        qual.setCareer(qualification.getCareer());
        qual.setJob(qualification.getJob());
        qual.setLevel(qualification.getLevel());
        qual.setRequirements(qualification.getRequirements());
        qualificationRepository.save(modelMapper.map(qual, Qualification.class));
    }

    // 증명 수정
    @Transactional
    public void modifyQual(Qualification qualification) {
        Qualification qual = qualificationRepository.findByUserIdAndQualificationsNumber(qualification.getUserId(), qualification.getQualificationsNumber());
        qual.setLevel(qualification.getLevel());
        qual.setCareer(qualification.getCareer());
        qual.setJob(qualification.getJob());
        qual.setRequirements(qualification.getRequirements());
    }

    // 증명 삭제
    @Transactional
    public void deleteQual(Qualification qualification) {
        qualificationRepository.deleteQualificationByUserIdAndQualificationsNumber(qualification.getUserId(), qualification.getQualificationsNumber());
    }


    // 증명번호 오름차순으로 정렬해서 조회
    public Qualification findTopByIdOrderByQualificationsNumDesc(String id) {
        Qualification qual = qualificationRepository.findTopByUserIdOrderByQualificationsNumberDesc(id);
        //int qualNumber = qual.getQualificationsNumber();
        return qual;
    }
}
