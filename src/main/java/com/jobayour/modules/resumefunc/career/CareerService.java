package com.jobayour.modules.resumefunc.career;

import com.jobayour.modules.resumefunc.resumeBasic.ResumeBasic;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CareerService {

    private CareerRepository careerRepository;
    private final ModelMapper modelMapper;

    public CareerService(CareerRepository careerRepository, ModelMapper modelMapper) {
        this.careerRepository = careerRepository;
        this.modelMapper = modelMapper;
    }

    //가장 최근 유저 경력 전체 조회(오름차순)
    public Career findTopByUserIdOrderByCareerNumDesc(String id){
        Career career = careerRepository.findTopByUserIdOrderByCareerNumDesc(id);
        return career;
    }



    // 유저 아이디와 이력서번호 찾아서 해당하는 경력 전체 조회
    public List<CareerDTO> findCareerById(String userId, int resumeNum) {
        List<Career> userCareerList = careerRepository.findByUserIdAndResumeNum(userId, resumeNum);
        List<CareerDTO> list = userCareerList.stream()
                .sorted(Comparator.comparing(Career::getCareerNum))
                .map(career -> modelMapper.map(career, CareerDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    // 유저 아이디와 이력서 번호, 경력 번호를 통해 경력 한개 조회(id,이력서번호,경력번호로만 조회하기)
    public CareerDTO findCareerByIdAndNum(String userId, int careerNum, int resumeNum) {
        Career userCareer = careerRepository.findByUserIdAndResumeNumAndCareerNum(userId, resumeNum,careerNum);
        CareerDTO list = modelMapper.map(userCareer, CareerDTO.class);
        return list;
    }

    // 유저 경력 추가
    public void addCareer(Career careerInfo) {
        Career career = new Career();
        career.setUserId(careerInfo.getUserId());
        career.setResumeNum(careerInfo.getResumeNum());
        career.setCompanyName(careerInfo.getCompanyName());
        career.setRetire(careerInfo.getRetire());
        career.setCareerStart(careerInfo.getCareerStart());
        career.setCareerEnd(careerInfo.getCareerEnd());
        career.setJobGradeDuties(careerInfo.getJobGradeDuties());
        career.setContents(careerInfo.getContents());
        career.setDeptName(careerInfo.getDeptName());
        career.setSalary(careerInfo.getSalary());
        career.setCurrency(careerInfo.getCurrency());
        career.setArea(careerInfo.getArea());
        careerRepository.save(modelMapper.map(career, Career.class));
    }


    @Transactional
    public void modifyCareer(Career careerInfo) {
        Career career = careerRepository.findByUserIdAndResumeNumAndCareerNum(careerInfo.getUserId(), careerInfo.getResumeNum(), careerInfo.getCareerNum());
        career.setCompanyName(careerInfo.getCompanyName());
        career.setRetire(careerInfo.getRetire());
        career.setCareerStart(careerInfo.getCareerStart());
        career.setCareerEnd(careerInfo.getCareerEnd());
        career.setJobGradeDuties(careerInfo.getJobGradeDuties());
        career.setContents(careerInfo.getContents());
        career.setDeptName(careerInfo.getDeptName());
        career.setSalary(careerInfo.getSalary());
        career.setCurrency(careerInfo.getCurrency());
        career.setArea(careerInfo.getArea());
    }

    @Transactional
    public void deleteCareer(String userId, int resumeNum, int careerNum) {
        careerRepository.deleteCareerByUserIdAndResumeNumAndCareerNum(userId, resumeNum, careerNum);
    }
}
