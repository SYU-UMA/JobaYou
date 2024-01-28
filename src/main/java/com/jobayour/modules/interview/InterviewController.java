package com.jobayour.modules.interview;

import com.jobayour.jwt.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/interview")
public class InterviewController {

    private InterviewService interviewService;

    private final JwtTokenProvider jwtTokenProvider;

    public InterviewController(InterviewService interviewService, JwtTokenProvider jwtTokenProvider) {
        this.interviewService = interviewService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 인터뷰 리스트 조회
    @GetMapping("/list")
    public List<InterviewDTO> interviewList(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        List<InterviewDTO> interviewList = interviewService.findInterviewByUserId(userId);
        return interviewList;
    }

    // 이력서 별 조회
    @GetMapping("/list/resumeNum")
    public List<InterviewDTO> resumeNuminterviewList(HttpServletRequest request,@RequestParam int resumeNum){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        List<InterviewDTO> interviewList = interviewService.findInterviewByUserIdAndResumeNum(userId, resumeNum);
        return interviewList;
    }

    // 인터뷰 리스트 백엔드 엔지니어 최근 5개 전체 조회
    @GetMapping("/recentlist/back")
    public List<InterviewDTO> recentbackinterviewList(){
        List<InterviewDTO> interviewList = interviewService.findTop5ByJobOrderByInterviewNumberDesc("백엔드 엔지니어");
        return interviewList;
    }
    // 인터뷰 리스트 프론트 엔지니어 최근 5개 전체 조회
    @GetMapping("/recentlist/front")
    public List<InterviewDTO> recentfrontinterviewList(){
        List<InterviewDTO> interviewList = interviewService.findTop5ByJobOrderByInterviewNumberDesc("프론트엔드 엔지니어");
        return interviewList;
    }
    // 인터뷰 리스트 데이터 엔지니어 최근 5개 전체 조회
    @GetMapping("/recentlist/data")
    public List<InterviewDTO> recentdatainterviewList(){
        List<InterviewDTO> interviewList = interviewService.findTop5ByJobOrderByInterviewNumberDesc("데이터 엔지니어");
        return interviewList;
    }




    // 최근 인터뷰 5개 리스트 조회 (인터뷰번호 오름차순으로 정렬후 5개 가져오기 방식)
    @GetMapping("/recentlist")
    public List<InterviewDTO> recentinterviewList(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        List<InterviewDTO> interviewList = interviewService.findTop5ByUserIdOrderByInterviewNumberDesc(userId);
        return interviewList;
    }

    // 인터뷰 추가
    @PostMapping("/add")
    public String InterviewAdd(@RequestBody Interview interview,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        interview.setUserId(userId);
        interviewService.addInterview(interview);
        return "추가완료";
    }

    // 인터뷰 삭제
    @DeleteMapping("/delete")
    public String InterviewDelete(@RequestBody Interview interview,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        interview.setUserId(userId);
        interviewService.deleteInterview(interview);
        return "삭제완료";
    }
}