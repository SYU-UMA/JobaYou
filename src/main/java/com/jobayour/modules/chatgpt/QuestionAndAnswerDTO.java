package com.jobayour.modules.chatgpt;

public class QuestionAndAnswerDTO {

    private String question;        //질문
    private String answer;          //답변



    public QuestionAndAnswerDTO(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // question이 비어있지 않고, answer도 비어있지 않은지 검사하는 메서드
    public boolean isQuestionAndAnswerValid() {
        return question != null && !question.isEmpty() &&
                answer != null && !answer.isEmpty();
    }


}
