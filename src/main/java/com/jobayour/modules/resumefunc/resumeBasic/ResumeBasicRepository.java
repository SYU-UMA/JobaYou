package com.jobayour.modules.resumefunc.resumeBasic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeBasicRepository extends JpaRepository<ResumeBasic, String> {

    List<ResumeBasic> findAllByUserId(String id);

    ResumeBasic findByResumeNumAndUserId(int num, String id);
    ResumeBasic findTopByUserId(String id);;

    void deleteUserResumeByResumeNumAndAndUserId(int num, String id);
}
