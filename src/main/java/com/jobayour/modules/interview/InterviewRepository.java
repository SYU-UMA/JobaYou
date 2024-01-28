package com.jobayour.modules.interview;
//수정
import com.jobayour.modules.interview.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, String> {

    List<Interview> findInterviewByUserId(String id);
    List<Interview> findInterviewByUserIdAndResumeNum(String id,int num);

    List<Interview> findTop5ByUserIdOrderByInterviewNumberDesc(String id);

    List<Interview> findTop5ByJobOrderByInterviewNumberDesc(String job);

    void deleteByUserIdAndInterviewNumber(String id, int interviewNumber);
}