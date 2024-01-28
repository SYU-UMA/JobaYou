package com.jobayour.modules.interview;

import com.jobayour.modules.interview.InterviewDTO;
import com.jobayour.modules.interview.Interview;
import com.jobayour.modules.qualification.Qualification;
import com.jobayour.modules.interview.InterviewRepository;
import com.jobayour.modules.qualification.QualificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
//수정
@Service
public class InterviewService {

    private InterviewRepository interviewRepository;
    private QualificationRepository qualificationRepository;

    private final ModelMapper modelMapper;

    public InterviewService(InterviewRepository interviewRepository, QualificationRepository qualificationRepository,ModelMapper modelMapper) {
        this.interviewRepository = interviewRepository;
        this.qualificationRepository = qualificationRepository;
        this.modelMapper = modelMapper;
    }


    // 인터뷰 리스트 조회
    public List<InterviewDTO> findInterviewByUserId(String id) {
        List<Interview> interviewList = interviewRepository.findInterviewByUserId(id);
        List<InterviewDTO> list = interviewList.stream()
                .sorted(Comparator.comparing(Interview::getQualificationsNumber))
                .map(interview -> modelMapper.map(interview, InterviewDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    // 인터뷰 최근 5개 조회
    public List<InterviewDTO> findTop5ByUserIdOrderByInterviewNumberDesc(String id) {
        List<Interview> interviewList = interviewRepository.findTop5ByUserIdOrderByInterviewNumberDesc(id);
        List<InterviewDTO> list = interviewList.stream()
                .sorted(Comparator.comparing(Interview::getQualificationsNumber))
                .map(interview -> modelMapper.map(interview, InterviewDTO.class))
                .collect(Collectors.toList());
        return list;
    }
    // 인터뷰 추가
    public void addInterview(Interview interview) {
        Interview interview1 = new Interview();
        interview1.setUserId(interview.getUserId());
        interview1.setInterviewAnswer(interview.getInterviewAnswer());
        interview1.setInterviewQuestion(interview.getInterviewQuestion());
        interview1.setJob(interview.getJob());
        interview1.setResumeNum(interview.getResumeNum());
        // 아이디에 맞는 최근 qualificationsNumber 세팅
        Qualification qual = qualificationRepository.findTopByUserIdOrderByQualificationsNumberDesc(interview.getUserId());
        interview1.setQualificationsNumber(qual.getQualificationsNumber());
        interviewRepository.save(modelMapper.map(interview1, Interview.class));
    }


    // 인터뷰 삭제
    @Transactional
    public void deleteInterview(Interview interview) {
        interviewRepository.deleteByUserIdAndInterviewNumber(interview.getUserId(), interview.getInterviewNumber());;
    }


    // 최근 5개 전체 조회 (JOB직군에 따라)

    public List<InterviewDTO> findTop5ByJobOrderByInterviewNumberDesc(String job) {
        List<Interview> interviewList = interviewRepository.findTop5ByJobOrderByInterviewNumberDesc(job);
        List<InterviewDTO> list = interviewList.stream()
                .sorted(Comparator.comparing(Interview::getQualificationsNumber))
                .map(interview -> modelMapper.map(interview, InterviewDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    public List<InterviewDTO> findInterviewByUserIdAndResumeNum(String userId, int resumeNum) {
        List<Interview> interviewList = interviewRepository.findInterviewByUserIdAndResumeNum(userId, resumeNum);
        List<InterviewDTO> list = interviewList.stream()
                .sorted(Comparator.comparing(Interview::getQualificationsNumber))
                .map(interview -> modelMapper.map(interview, InterviewDTO.class))
                .collect(Collectors.toList());
        return list;
    }
}