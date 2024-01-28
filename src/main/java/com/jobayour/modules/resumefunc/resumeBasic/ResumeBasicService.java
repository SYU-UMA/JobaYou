package com.jobayour.modules.resumefunc.resumeBasic;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeBasicService {

    private ResumeBasicRepository resumeBasicRepository;
    private final ModelMapper modelMapper;

    public ResumeBasicService(ResumeBasicRepository resumeBasicRepository, ModelMapper modelMapper) {
        this.resumeBasicRepository = resumeBasicRepository;
        this.modelMapper = modelMapper;
    }

    //개인 유저 이력 전체 조회
    public List<ResumeBasicDTO> findAllUserResume(String userId) {
        List<ResumeBasic> resumeBasicList = resumeBasicRepository.findAllByUserId(userId);
        List<ResumeBasicDTO> list = resumeBasicList.stream()
                .sorted(Comparator.comparing(ResumeBasic::getUserId))
                .map(userResume -> modelMapper.map(userResume, ResumeBasicDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    //개인 유저아이디와 번호로 해당하는 이력서 조회
    public ResumeBasic findResumeByIdAndnum(String id, int resumeNum) {
        ResumeBasic resumeBasic = resumeBasicRepository.findByResumeNumAndUserId(resumeNum, id);
        return resumeBasic;
    }

    // 유저 이력 추가
    public void addUserResume(ResumeBasic resumeinfoBasic) {
        ResumeBasic resumeBasic = new ResumeBasic();
        resumeBasic.setUserId(resumeinfoBasic.getUserId());
        resumeBasic.setResumeName(resumeinfoBasic.getResumeName());
        resumeBasic.setCareer(resumeinfoBasic.getCareer());
        resumeBasic.setUserAddress(resumeinfoBasic.getUserAddress());
        resumeBasicRepository.save(modelMapper.map(resumeBasic, ResumeBasic.class));
    }

    // 개인 유저 이력 수정
    @Transactional
    public void modifyUserResume(ResumeBasic resumeinfoBasic) {
        ResumeBasic resumeBasic = resumeBasicRepository
                .findByResumeNumAndUserId(resumeinfoBasic.getResumeNum(), resumeinfoBasic.getUserId());
        resumeBasic.setUserId(resumeinfoBasic.getUserId());
        resumeBasic.setResumeName(resumeinfoBasic.getResumeName());
        resumeBasic.setCareer(resumeinfoBasic.getCareer());
        resumeBasic.setUserAddress(resumeinfoBasic.getUserAddress());

    }

    // 개인 유저 이력 삭제
    @Transactional
    public void deleteUserResume(String userId, int resumeNum) {
        resumeBasicRepository.deleteUserResumeByResumeNumAndAndUserId(resumeNum, userId);
    }

    // 개인 최근 이력 넘버 조회
    public int findRecentNum(String userId) {
        ResumeBasic resumeBasic = resumeBasicRepository.findTopByUserId(userId);
        return resumeBasic.getResumeNum();
    }
}

