import React from "react";
import cn from "classnames";

import { Input, Button } from "reactstrap";

import styles from "./Sign.module.scss";

import { useNavigate } from "react-router-dom";

import axios from "axios";

import { useDispatch } from "react-redux";
import { SigninUser } from "../../../api/Users";
import { setAccessToken, setRefreshToken } from "../../../storage/Cookie";
import { SET_TOKEN } from "../../../Store/Auth";
import { useRecoilState } from "recoil";
import { isLoginState, userIdState } from "../../../recoil/RecoilState";

const SignIn = () => {
  const navigate = useNavigate();

  const movePage = (url) => {
    navigate(url);
  };

  const [isLogin, setIsLogin] = useRecoilState(isLoginState);
  const [recoilUserId, setRecoilUserId] = useRecoilState(userIdState);
  const [userId, setUserId] = React.useState("");
  const [userPwd, setuserPwd] = React.useState("");

  const config = { "Content-Type": "application/json" };

  const onClickSignIn = async () => { 
       // 백으로부터 받은 응답
      const response = await SigninUser({userId, userPwd});
      if(response.status === 403) {
        alert('아이디 혹은 비밀번호 일치하지 않습니다.');
      }
      else if (response.status) {
        //쿠키에 Access Token 저장
        setAccessToken(response.text);
        setIsLogin(true);
        setRecoilUserId(userId);
        return navigate('/');
      }
  };

  return (
    <div className={styles.Main}>
      <div className={styles.Section}>
        <div>
          <Input
            id="userId"
            type="text"
            placeholder="ID"
            onChange={(e) => setUserId(e.target.value)}
          />
        </div>
        <div>
          <Input
            id="userPwd"
            type="password"
            placeholder="PASSWORD"
            onChange={(e) => setuserPwd(e.target.value)}
          />
        </div>
      </div>
      <div className={styles.ButtonSection}>
        <Button onClick={onClickSignIn} size="lg" color="primary" block>
          로그인
        </Button>
        <Button
          color="primary"
          onClick={() => movePage("/signUp")}
          size="lg"
          block
        >
          회원가입
        </Button>
      </div>
    </div>
  );
};

export default SignIn;
