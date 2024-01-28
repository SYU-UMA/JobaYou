package com.jobayour.modules.chatgpt;

import com.jobayour.jwt.JwtTokenProvider;
import com.jobayour.modules.interview.Interview;
import com.jobayour.modules.interview.InterviewService;
import com.jobayour.modules.qualification.Qualification;
import com.jobayour.modules.qualification.QualificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;


@Service
@RequiredArgsConstructor
public class ChatgptNoResumeService {

    private final InterviewService interviewService;    //db저장을 위한 의존성 주입

    private final ChatService chatService;      //chatgpt api통신을 위한 클래스

    private final QualificationService qualificationService;    //db조회를 위한 위존성 주입

    private final JwtTokenProvider jwtTokenProvider;            //jwt 토큰 정보를 받기위한 Provider

    public void test(HttpServletRequest request){

        int checkNumber = 5;        // 5개의 질문과 답변을 저장하기위해 체크하는 정보

        String token = jwtTokenProvider.resolveToken(request); //HttpServletRequest에서 jwt토큰 추출

        String userId = jwtTokenProvider.getUserId(token);      //jwt토큰에서 userId추출

        // quallist db조회해서 필요한 데이터 가져오기
        Qualification getData = qualificationService.findTopByIdOrderByQualificationsNumDesc(userId);

        String combinedQuestions = "너는 이제 회사의 면접관이야."
                +"회사에서 뽑으려고 하는 직무는 " + getData.getJob()
                + "이고 뽑으려고 하는 연차는 " + getData.getCareer()
                + "이상이고 너가 하려고 하는 질문 난이도는 최저1에서부터 최고5라고 하면 " + getData.getLevel()
                + "이고 뽑으려고 하는 직무의 자격요건은 " + getData.getRequirements()+"이야"
                + "알려준 정보만을 가지고 너가 할 수 있는 예상면접질문 1가지와 답변까지 알려줘. "
                + " 질문과 답변사이에 \\를 꼭 넣어서 구분해줘 그리고 너가 나에게 줄 값의 형태를 알려줄게."
                + "Q.질문내용 ? A.답변내용."
                +"이런식으로 예상질문 뒤에는 꼭 ? 로 끝나게해주고 한글로알려줘.그리고 예시로 알려준 내용은 만들어줄 질문과 답변에 상관없는 내용이야 그러니까 질문과 답변에 들어가지않게해.";

        while(checkNumber>0){

            String response = chatService.getChatResponse(combinedQuestions);

            String[] pairs = response.split("\\?");

            if(pairs.length >= 2) {
                String question = pairs[0].trim();
                String answer = pairs[1].trim();
                if (question.startsWith("Q") && answer.startsWith("A")) {
                    QuestionAndAnswerDTO questionAndAnswerDTO = new QuestionAndAnswerDTO(question, answer);

                    //question과 answer 비어있지 않은지에 대한 여부 확인 후 저장
                    if (questionAndAnswerDTO.isQuestionAndAnswerValid()) {

                        Interview interview = new Interview(getData.getQualificationsNumber()
                                , userId,getData.getJob(),questionAndAnswerDTO.getQuestion(), questionAndAnswerDTO.getAnswer());

                        interviewService.addInterview(interview);

                        checkNumber--;
                    }else{
                        break;
                    }
                }
            }
        }
    }
}
