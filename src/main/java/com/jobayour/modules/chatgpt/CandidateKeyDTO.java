package com.jobayour.modules.chatgpt;

public class CandidateKeyDTO {


    private int resumeNum;


    public CandidateKeyDTO(int resumeNum) {
        this.resumeNum = resumeNum;
    }

    public CandidateKeyDTO() {
    }

    public int getResumeNum() {
        return resumeNum;
    }

    public void setResumeNum(int resumeNum) {
        this.resumeNum = resumeNum;
    }

    @Override
    public String toString() {
        return "CandidateKeyDTO{" +
                "resumeNum=" + resumeNum +
                '}';
    }
}
