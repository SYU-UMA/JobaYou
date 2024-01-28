package com.jobayour.modules.resumefunc.myCareer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCareerRepository extends JpaRepository<MyCareer, String> {

    MyCareer findByUserIdAndResumeNum(String userId, int resumeNum);
    MyCareer findByUserIdAndResumeNumAndMyCareerNum(String userId, int resumeNum, int mycareerNum);

    void deleteByUserIdAndAndResumeNumAndMyCareerNum(String userId, int resumeNum, int careerNum);
}
