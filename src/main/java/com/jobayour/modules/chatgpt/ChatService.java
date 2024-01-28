package com.jobayour.modules.chatgpt;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChatService {                      // 챗gpt에 string변수를 보내서 리터만 받는 서비스

    private final ChatgptService chatgptService;     //챗gpt 라이브러리에서 제공하는 클래스

    public String getChatResponse(String prompt) {
        // 챗gpt에 질문해서 답변 받기
        return chatgptService.sendMessage(prompt);

    }

}
