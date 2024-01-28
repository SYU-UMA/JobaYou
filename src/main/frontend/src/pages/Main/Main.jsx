import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import styles from "./Main.module.scss";
import { useLocation } from "react-router-dom";

import Navigation from "../../components/Navigation/Navigation";

import NewForm from "./NewForm/NewForm";

import JobCard from "./JobCard/JobCard";

import Header from "../../components/Header/Header";
import { setAccessToken } from "../../storage/Cookie";
import GNB from "../../components/GNB/GNB";

const Main = () => {
  const location = useLocation();

  return (
    <div className={styles.Main}>
      <GNB />
      <div className={styles.Container}>
        <Navigation />
      </div>
    </div>
  );
};

export default Main;
