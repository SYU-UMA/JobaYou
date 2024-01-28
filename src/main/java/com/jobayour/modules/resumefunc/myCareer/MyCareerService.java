package com.jobayour.modules.resumefunc.myCareer;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyCareerService {

    private MyCareerRepository myCareerRepository;
    private final ModelMapper modelMapper;

    public MyCareerService(MyCareerRepository myCareerRepository, ModelMapper modelMapper) {
        this.myCareerRepository = myCareerRepository;
        this.modelMapper = modelMapper;
    }

    // 나의 커리어 조회
    public MyCareerDTO findMyCareer(String userId, int resumeNum) {
        MyCareer myCareer = myCareerRepository.findByUserIdAndResumeNum(userId, resumeNum);
        if (myCareer == null) {
            return null; // 또는 적절한 처리를 수행
        }
        MyCareerDTO myCareerDTO = modelMapper.map(myCareer, MyCareerDTO.class);
        return myCareerDTO;
    }

    // 나의 커리어 추가
    public void addMyCareer(MyCareer myCareer) {
        MyCareer myCareer1 = new MyCareer();
        myCareer1.setUserId(myCareer.getUserId());
        myCareer1.setResumeNum(myCareer.getResumeNum());
        myCareer1.setJob(myCareer.getJob());
        myCareer1.setIntroduction(myCareer.getIntroduction());
        myCareer1.setUrl(myCareer.getUrl());
        myCareer1.setCoreCompetence1(myCareer.getCoreCompetence1());
        myCareer1.setCoreCompetence2(myCareer.getCoreCompetence2());
        myCareer1.setCoreCompetence3(myCareer.getCoreCompetence3());
        myCareer1.setCoreCompetence4(myCareer.getCoreCompetence4());
        myCareer1.setCoreCompetence5(myCareer.getCoreCompetence5());
        myCareerRepository.save(modelMapper.map(myCareer1, MyCareer.class));
    }

    @Transactional
    public void modifyMyCareer(MyCareer myCareer) {
        MyCareer myCareer1 = myCareerRepository.findByUserIdAndResumeNumAndMyCareerNum(myCareer.getUserId(), myCareer.getResumeNum(), myCareer.getMyCareerNum());
        myCareer1.setUserId(myCareer.getUserId());
        myCareer1.setJob(myCareer.getJob());
        myCareer1.setIntroduction(myCareer.getIntroduction());
        myCareer1.setUrl(myCareer.getUrl());
        myCareer1.setCoreCompetence1(myCareer.getCoreCompetence1());
        myCareer1.setCoreCompetence2(myCareer.getCoreCompetence2());
        myCareer1.setCoreCompetence3(myCareer.getCoreCompetence3());
        myCareer1.setCoreCompetence4(myCareer.getCoreCompetence4());
        myCareer1.setCoreCompetence5(myCareer.getCoreCompetence5());
    }

    @Transactional
    public void deleteMyCareer(String userId,int resumeNum,int myCareerNum) {
        myCareerRepository.deleteByUserIdAndAndResumeNumAndMyCareerNum(userId, resumeNum, myCareerNum);
    }
}
