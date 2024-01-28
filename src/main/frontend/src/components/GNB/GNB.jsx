import React from "react";
import { Link } from "react-router-dom";
import styles from "./GNB.module.scss";
import { useLocation } from "react-router-dom";
const GNB = () => {
  const location = useLocation();
  const pathname = location.pathname;

  return (
    <div className={styles.Navigation}>
      <Link
        className={styles.Link}
        style={{
          color: pathname === "/" ? "#357fc9" : "#000000",
        }}
        to={"/"}
      >
        메인
      </Link>
      <Link
        className={styles.Link}
        to={"/resumeManage"}
        style={{
          color: pathname === "/resumeManage" ? "#357fc9" : "#000000",
        }}
      >
        이력서 관리
      </Link>
      <Link
        className={styles.Link}
        to={"/myPage"}
        style={{
          color: pathname === "/myPage" ? "#357fc9" : "#000000",
        }}
      >
        마이페이지
      </Link>
    </div>
  );
};

export default GNB;
