package com.jobayour.modules.qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, String> {

    // 유저의 qual 리스트
    //@Query("select q from Qualification q where q.id = ?1")
    List<Qualification> findQualificationByUserId(String id);

    Qualification findByUserIdAndQualificationsNumber(String id, int qualificationsnum);

    // 유저의 qual 삭제
    void deleteQualificationByUserIdAndQualificationsNumber(String id, int qualificationsnum);

    Qualification findTopByUserIdOrderByQualificationsNumberDesc(String id);

}