import React, { useEffect } from "react";

import styles from "./Header.module.scss";
import { Link, useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import { isLoginState, userIdState } from "../../recoil/RecoilState";
import { getCookieAccessToken, removeAccessCookieToken } from "../../storage/Cookie";
import AxiosInstance from "../../api/AxiosInstance";

const Header = () => {
  const navigate = useNavigate();

  const movePage = (url) => {
    navigate(url);
  };

  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [recoilUserId, setRecoilUserId] = useRecoilState(userIdState);

  const findUserId = async() => {
    try{
    await  AxiosInstance
    .get('/api/auth/findUserId')
    .then((response)=> {
      setRecoilUserId(response.data)
    })
    } catch (error) {
      handleAxiosError(error);
    }
  }

 const handleAxiosError = (error) => {
  if (error.response.status === 400){
    alert('잘못된 토큰입니다. 다시 로그인해주세요.');
    movePage('/signIn');
    setIsLogin(false);
    removeAccessCookieToken();
  } else if (error.response.status === 401) {
    alert('토큰이 만료되었습니다. 다시 로그인해주세요.')
    movePage('/signIn');
    setIsLogin(false);
    removeAccessCookieToken();
  } else if (error.response.status === 402) {
    alert('비정상적인 토큰입니다. 다시 로그인해주세요.')
    movePage('/signIn');
    setIsLogin(false);
    removeAccessCookieToken();
  }
 }

  useEffect(() => {
    if (getCookieAccessToken() != null) {
      setIsLogin(true);
      findUserId();
    }
  }, []);

  return (
    <div className={styles.Header}>
      <h2 className={styles.Logo} onClick={() => movePage("/")}>
        <img src="img/favicon_soy.png" alt="icon" />
        <span>Jobayou</span>
      </h2>
      <div className={styles.AuthConatiner}>
        {!isLogin && (
          <div className={styles.SignIn}>
            <Link
              to={"/signIn"}
              style={{ color: "black", textDecoration: "none" }}
            >
              Login
            </Link>
          </div>
        )}
        {isLogin && (
          <div className={styles.SignIn}>
            <Link
              to={"/signOut"}
              style={{ color: "black", textDecoration: "none" }}
            >
              Logout
            </Link>
          </div>
        )}
      </div>
    </div>
  );
};

export default Header;
