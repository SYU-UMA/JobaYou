package com.jobayour.modules.resumefunc.skill;

import com.jobayour.jwt.JwtTokenProvider;
import com.jobayour.modules.resumefunc.resumeBasic.ResumeBasic;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/skill")
public class SkillController {
    private SkillService skillService;
    private JwtTokenProvider jwtTokenProvider;

    public SkillController(SkillService skillService, JwtTokenProvider jwtTokenProvider) {
        this.skillService = skillService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 유저 이력에 있는 스킬 조회
    @GetMapping("/list")
    public List<SkillDTO> SkillList(@RequestParam int resumeNum, HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출

        List<SkillDTO> skillList = skillService.skillListbyIdAndUserId(resumeNum, userId);
        return skillList;
    }

    // 유저 스킬 한개 추가
    @PostMapping("/add")
    public String skillAdd(@RequestBody Skill skill,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        skill.setUserId(userId);
        if("".equals(skill.getSkill())){
            return "추가 안됨";
        }
        skillService.addSkill(skill);
        return "스킬 추가 완료";
    }

    // 스킬 수정 (아이디/스킬넘버/이력넘버)
    @PostMapping("/modify")
    public String SkillModify(@RequestBody Skill skill,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        skill.setUserId(userId);
        skillService.modifySkill(skill);
        return "수정완료";
    }

    // 스킬 삭제 (아이디/스킬넘버/이력넘버)
    @DeleteMapping("/delete")
    public String SkillDelete(@RequestParam int skillNum, @RequestParam int resumeNum,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출

        skillService.deleteSkill(userId, resumeNum, skillNum);
        return "삭제완료";
    }

}
