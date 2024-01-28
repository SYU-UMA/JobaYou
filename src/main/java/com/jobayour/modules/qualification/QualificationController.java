package com.jobayour.modules.qualification;

import com.jobayour.jwt.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
@RequestMapping(value = "/qualification")
public class QualificationController {

    private QualificationService qualificationService;
    private final JwtTokenProvider jwtTokenProvider;

    public QualificationController(QualificationService qualificationService, JwtTokenProvider jwtTokenProvider) {
        this.qualificationService = qualificationService;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    // 유저아이디로 증명조회
    @GetMapping("/list")
        public List<QualificationDTO> qualList(HttpServletRequest request) {

        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출

        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출

            List<QualificationDTO> qualList = qualificationService.findqualById(userId);
            return qualList;
    }

    // 최근 증명 qual 조회
    @GetMapping("/qualnum")
    public Qualification qualNum(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        Qualification qual = qualificationService.findTopByIdOrderByQualificationsNumDesc(userId);
        return qual;
    }

    // 유저 증명 추가
    @PostMapping("/add")
    public String QualAdd(@RequestBody Qualification qualification,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        qualification.setUserId(userId);
        qualificationService.addQual(qualification);
        return "추가완료";
    }

    // 유저 증명 수정
    @PostMapping("/modify")
    public String QualModify(@RequestBody Qualification qualification,HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        qualification.setUserId(userId);
        List<QualificationDTO> qualList = qualificationService.findqualById(userId);
        if(qualList.size() == 0) {
            return "수정 안됬어요";
        }
        qualificationService.modifyQual(qualification);
        return "수정 완료";
    }

    // 유저 증명 삭제 인터뷰(질문답변)에 연결되어 있을 땐 삭제 불가, 질문답변 삭제후 인터뷰 삭제가능
    @DeleteMapping("/delete")
    public String QualDelete(@RequestBody Qualification qualification,HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출
        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출
        qualification.setUserId(userId);
        qualificationService.deleteQual(qualification);
        return "삭제 완료";
    }
}
