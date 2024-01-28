package com.jobayour.modules.resumefunc.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {

    List<Skill> findAllByUserIdAndResumeNum(String id, int num);

    Skill findBySkillNumAndUserIdAndResumeNum(int skillNum, String id, int resumeNum);

    void deleteBySkillNumAndUserIdAndResumeNum(int skillNum, String id, int resumeNum);

    //최근 가장 최근 유저 skill 전체 조회
    Skill findTopByUserIdOrderBySkillNumDesc(String id);

    // 유저 이름과 skillNum으로 이력서에 저장한 스킬 조회(1개)
    Skill findByUserIdAndSkillNum(String userId,int skillNum);
}
