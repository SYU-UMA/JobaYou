import React from "react";

import styles from "./Navigation.module.scss";
import { Link, useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import { interviewTypeState } from "../../recoil/RecoilState";
import JobCard from "../../pages/Main/JobCard/JobCard";

const Navigation = () => {
  const navigate = useNavigate();

  const movePage = (url) => {
    navigate(url);
  };

  //InterviewList를 담는 Recoilstate
  const [interviewType, setInterviewType] = useRecoilState(interviewTypeState);

  const links = [
    {
      title: "Resume List",
      description: "생성된 직무 인터뷰를 다시 확인 해보세요.",
      url: "/resumeList",
      type: "",
    },
    {
      title: "FrontEnd Engineer",
      description: "최근 프론트엔드 개발자 인터뷰를 확인 해보세요.",
      url: "/exInterviewList",
      type: "front",
    },
    {
      title: "BackEnd Engineer",
      description: "최근 백엔드 개발자 인터뷰를 확인 해보세요.",
      url: "/exInterviewList",
      type: "back",
    },
    {
      title: "Data Engineer",
      description: "최근 데이터 엔지니어 인터뷰를 확인 해보세요.",
      url: "/exInterviewList",
      type: "data",
    },
  ];

  return (
    <div className={styles.Header}>
      <div className={styles.Container}>
        <div className={styles.AuthConatiner}>
          <Link to={"/selectResume"} style={{ textDecoration: "none" }}>
            <div className={styles.AiBox}>
              <h1 className={styles.AiBox_Text}>Ai Interview</h1>
              <span className={styles.AiBox_Description}>
                생성형 Ai를 활용한 직무 인터뷰를 활용해보세요.
              </span>
            </div>
          </Link>
        </div>
        <div className={styles.JobContainer}>
          {links.map(({ title, description, url, type }) => (
            <div onClick={() => setInterviewType(type)}>
            <JobCard title={title} description={description} url={url} />
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Navigation;
