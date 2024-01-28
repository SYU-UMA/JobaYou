package com.jobayour.modules.resumefunc.career;


import com.jobayour.jwt.JwtTokenProvider;
import com.jobayour.modules.resumefunc.resumeBasic.ResumeBasic;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/career")
public class CareerController {

    private CareerService careerService;
    private final JwtTokenProvider jwtTokenProvider;

    public CareerController(CareerService careerService, JwtTokenProvider jwtTokenProvider) {
        this.careerService = careerService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 개인 경력 전체 조회(이력서 하나에 해당하는) 핸들러 메소드
    @GetMapping("/list")
    public List<CareerDTO> UserCareerList(@RequestParam int resumeNum, HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출

        List<CareerDTO> UserCareerList = careerService.findCareerById(userId, resumeNum);
        return UserCareerList;
    }
    // 개인 경력 한개 조회 핸들러 메소드
    @GetMapping("/list/num")
    public CareerDTO UserCareerListByNum(@RequestParam int resumeNum,@RequestParam int careerNum,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출

        CareerDTO UserCareerList = careerService.findCareerByIdAndNum(userId, resumeNum, careerNum);
        return UserCareerList;
    }

    // 경력 추가 핸들러 메소드
    @PostMapping("/add")
    public String CareerAdd(@RequestBody Career career,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        career.setUserId(userId);
        careerService.addCareer(career);
        return "경력 추가 완료";
    }

    // 경력 수정 핸들러 메소드
    @PostMapping("/modify")
    public String CareerModify(@RequestBody Career career,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        career.setUserId(userId);
        careerService.modifyCareer(career);
        return "경력 수정 완료";
    }

    // 경력 삭제 메소드
    @DeleteMapping("/delete") /* career.getUserId(), career.getResumeNum(), career.getCareerNum()) */
    public String CareerDelete(@RequestParam int resumeNum, @RequestParam int careerNum,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출

        careerService.deleteCareer(userId, resumeNum, careerNum);
        return "경력 삭제 완료";
    }
}
