package com.jobayour.modules.resumefunc.skill;

import java.io.Serializable;

public class SkillDTO implements Serializable {

    private int skillNum;
    private String userId;
    private int resumeNum;
    private String skill;

    public SkillDTO() {
    }

    public SkillDTO(int skillNum, String userId, int resumeNum, String skill) {
        this.skillNum = skillNum;
        this.userId = userId;
        this.resumeNum = resumeNum;
        this.skill = skill;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getResumeNum() {
        return resumeNum;
    }

    public void setResumeNum(int resumeNum) {
        this.resumeNum = resumeNum;
    }

    public int getSkillNum() {
        return skillNum;
    }

    public void setSkillNum(int skillNum) {
        this.skillNum = skillNum;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "SkillDTO{" +
                "skillNum=" + skillNum +
                ", userId='" + userId + '\'' +
                ", resumeNum=" + resumeNum +
                ", skill='" + skill + '\'' +
                '}';
    }
}
