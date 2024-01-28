import React from "react";

import styles from "./JobCard.module.scss";
import { Link } from "react-router-dom";

const JobCard = ({ title, description, url }) => {
  return (
    <Link to={url} style={{ textDecoration: "none" }}>
      <div className={styles.Container}>
        <h3 className={styles.Container_Title}>{title}</h3>
        <span className={styles.Container_Description}>{description}</span>
      </div>
    </Link>
  );
};

export default JobCard;
