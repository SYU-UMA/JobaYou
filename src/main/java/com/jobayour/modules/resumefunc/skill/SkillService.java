package com.jobayour.modules.resumefunc.skill;

import com.jobayour.modules.resumefunc.resumeBasic.ResumeBasic;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {
    private SkillRepository skillRepository;
    private final ModelMapper modelMapper;

    public SkillService(SkillRepository skillRepository, ModelMapper modelMapper) {
        this.skillRepository = skillRepository;
        this.modelMapper = modelMapper;
    }

    public Skill findTopByUserIdOrderBySkillNumDesc(String id){
        Skill skill = skillRepository.findTopByUserIdOrderBySkillNumDesc(id);
        return skill;
    }

    // 유저 이름과 skillNum으로 이력서에 저장한 스킬 조회(1개)
    public Skill findByUserIdAndSkillNum(String userId,int skillNum){
        Skill skill= skillRepository.findByUserIdAndSkillNum(userId,skillNum);
        return skill;
    }

    // 유저이름과 num로 이력서에 저장한 스킬 조회(전체)
    public List<SkillDTO> skillListbyIdAndUserId(int resumeNum, String userId) {
        List<Skill> skillList = skillRepository.findAllByUserIdAndResumeNum(userId, resumeNum);
        List<SkillDTO> list = skillList.stream()
                .sorted(Comparator.comparing(Skill::getSkillNum))
                .map(skill -> modelMapper.map(skill,SkillDTO.class))
                .collect(Collectors.toList());
        return list;
    }
    // 유저 스킬 추가 (아이디/이력서번호/스킬내용)
    public void addSkill(Skill skillinfo) {
        Skill skill = new Skill();
        skill.setSkill(skillinfo.getSkill());
        skill.setUserId(skillinfo.getUserId());
        skill.setResumeNum(skillinfo.getResumeNum());
        skillRepository.save(modelMapper.map(skill, Skill.class));
    }

    // 스킬 내용 수정
    @Transactional
    public void modifySkill(Skill skillinfo) {
        Skill skill = skillRepository.findBySkillNumAndUserIdAndResumeNum(skillinfo.getSkillNum(), skillinfo.getUserId(), skillinfo.getResumeNum());
        skill.setSkill(skillinfo.getSkill());
    }

    // 스킬 삭제
    @Transactional
    public void deleteSkill(String userId,int resumeNum,int skillNum) {
        skillRepository.deleteBySkillNumAndUserIdAndResumeNum(skillNum, userId, resumeNum);
    }
}

