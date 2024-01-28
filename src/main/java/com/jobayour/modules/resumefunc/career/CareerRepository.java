package com.jobayour.modules.resumefunc.career;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerRepository extends JpaRepository<Career, String> {

    List<Career> findByUserIdAndResumeNum(String userId, int resumeNum);
    Career findByUserIdAndResumeNumAndCareerNum(String userid, int resumenum, int careermum);

    void deleteCareerByUserIdAndResumeNumAndCareerNum(String userId, int resumeNum, int careerNum);

    //최근 가장 최근 유저 경력 조회
    Career findTopByUserIdOrderByCareerNumDesc(String id);
}
