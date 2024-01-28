package com.jobayour.modules.resumefunc.myCareer;

import com.jobayour.jwt.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mycareer")
public class MyCareerController {

    private MyCareerService myCareerService;

    private final JwtTokenProvider jwtTokenProvider;

    public MyCareerController(MyCareerService myCareerService, JwtTokenProvider jwtTokenProvider) {
        this.myCareerService = myCareerService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 내 커리어 조회
    @GetMapping("/select")
    public MyCareerDTO MyCareerSelect(HttpServletRequest request, @RequestParam int resumeNum){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        MyCareerDTO myCareerDTO = myCareerService.findMyCareer(userId, resumeNum);
        return myCareerDTO;
    }
    @PostMapping("/add")
    public String MyCareerAdd(@RequestBody MyCareer myCareer, HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);
        myCareer.setUserId(userId);
        myCareerService.addMyCareer(myCareer);

        return "추가 완료";
    }

    // 나의 이력서 수정 핸들러 메소드
    @PostMapping("/modify")
    public String MyCareerModify(@RequestBody MyCareer myCareer, HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);
        myCareer.setUserId(userId);
        myCareerService.modifyMyCareer(myCareer);

        return "수정 완료";
    }
    @DeleteMapping("/delete")
    public String MyCareerDelete(@RequestParam int resumeNum, @RequestParam int myCareerNum,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);

        myCareerService.deleteMyCareer(userId, resumeNum, myCareerNum);
        return "삭제 완료";
    }
}
