package com.jobayour.modules.resumefunc.resumeBasic;


import com.jobayour.jwt.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/resume")
public class ResumeBasicController {

    private ResumeBasicService userResumeservice;
    private final JwtTokenProvider jwtTokenProvider;

    public ResumeBasicController(ResumeBasicService userResumeservice, JwtTokenProvider jwtTokenProvider) {
        this.userResumeservice = userResumeservice;
        this.jwtTokenProvider = jwtTokenProvider;
    }



    // 개인 유저 이력 전체 조회 핸들러 메소드
    @GetMapping("/list")
    public List<ResumeBasicDTO> UserResumeList(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        List<ResumeBasicDTO> userResumeList = userResumeservice.findAllUserResume(userId);
        return userResumeList;
    }

    // 개인 유저 이력 한개 조회
    @GetMapping("/list/num")
    public ResumeBasic UserResumeNum(HttpServletRequest request, @RequestParam int resumeNum){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        ResumeBasic resumeBasic = userResumeservice.findResumeByIdAndnum(userId, resumeNum);
        return resumeBasic;
    }
    // 개인 유저 이력 제목들만 조회하는 핸들러 메소드
    @GetMapping("/list/name")
    public List<String> UserResumeNameList(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        List<ResumeBasicDTO> userResumeList = userResumeservice.findAllUserResume(userId);
        return userResumeList.stream()
                .map(ResumeBasicDTO::getResumeName)
                .collect(Collectors.toList());
    }
    // 개인 유저 최근 이력 번호 가져오는 핸들러 메소드
    @PostMapping("/recent/resumeNum")
    public int UserResumeRecentNum(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        int recentResumeNum = userResumeservice.findRecentNum(userId);
        return recentResumeNum;
    }

    // 개인 유저 이력 추가 핸들러 메소드
    @PostMapping("/add")
    public String UserResumeAdd(@RequestBody ResumeBasic resumeBasic, HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        resumeBasic.setUserId(userId);
        userResumeservice.addUserResume(resumeBasic);
        return "유저 이력 추가 완료";
    }


    // 개인 유저 이력 수정 핸들러 메소드
    @PostMapping("/modify")
    public String UserResumeModify(@RequestBody ResumeBasic resumeBasic, HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        resumeBasic.setUserId(userId);
        userResumeservice.modifyUserResume(resumeBasic);
        return "개인 유저 이력 수정 완료";
    }

    // 개인 유저 이력 삭제 핸들러 메소드(이력이 사용된 것들 다 같이 삭제)
    @DeleteMapping("/delete")
    public String UserResumeDelete(@RequestParam int resumeNum, HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출

        userResumeservice.deleteUserResume(userId, resumeNum);
        return "개인 유저 이력삭제완료";
    }

}
