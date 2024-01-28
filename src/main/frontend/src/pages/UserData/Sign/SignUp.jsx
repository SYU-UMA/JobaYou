import React from "react";

import { Input, Button } from "reactstrap";

import styles from "./Sign.module.scss";

import { useNavigate } from "react-router-dom";

import axios from "axios";
import AxiosInstance from "../../../api/AxiosInstance";

const SignUp = () => {
  const navigate = useNavigate();
  
  const movePage = (url) => {
    navigate(url)
  };

  const [userId, setUserId] = React.useState("");
  const [userPassword, setUserPassword] = React.useState("");

  const [userData, setUserData] = React.useState({
    userId: null,
    userPassword: null,
    userName: null,
    userBirth: null,
    userPhone: null,
    userEmail: null,
  });

    const onClickSignUp = () => {
      axios
          .post('/api/auth/register', {
            userId: userData.userId,
            userPwd: userData.userPassword,
            userName: userData.userName,
            userBirthday: userData.userBirth,
            userPhone: userData.userPhone,
            userEmail: userData.userEmail,
          })
          .then(() => {
            movePage('/signIn');
            alert("회원가입 성공!");
          })
          .catch((error) => handleAxiosError(error));
    }

    const handleAxiosError = (error) => {
      if (error.response.status === 500){
        alert('중복된 아이디입니다!');
      }
     }

    return (
      <div className={styles.Main}>
        <div className={styles.Section}>
          <div>
            <h5>아이디</h5>
            <Input
              id="userId"
              type="text"
              placeholder="몇자리/ 영문,숫자, 특수문자 사용가능"
              onChange={(e) => setUserData(prev => ({ ...prev, userId: e.target.value}))}
            />
          </div>
          <div>
            <h5>비밀번호</h5>
            <Input
              id="userPassword"
              type="password"
              placeholder="몇자/ 영문, 숫자, 특수문자 조합"
              onChange={(e) => setUserData(prev => ({ ...prev, userPassword: e.target.value}))}
            />
          </div>
          <div>
            <h5>이름</h5>
            <Input
              id="userName"
              type="text"
              placeholder="이름 입력"
              onChange={(e) => setUserData(prev => ({ ...prev, userName: e.target.value}))}
            />
          </div>
          <div>
            <h5>생년월일</h5>
            <Input
              id="userBirth"
              type="text"
              placeholder="YYYYMMDD"
              onChange={(e) => setUserData(prev => ({ ...prev, userBirth: e.target.value}))}
            />
          </div>
          <div>
            <h5>휴대폰</h5>
            <Input
              id="userPhone"
              type="text"
              placeholder="'-'빼고 숫자만 입력"
              onChange={(e) => setUserData(prev => ({ ...prev, userPhone: e.target.value}))}
            />
          </div>
          <div>
            <h5>이메일</h5>
            <Input
              id="userEmail"
              type="text"
              placeholder="jobayou@jobayou.com"
              onChange={(e) => setUserData(prev => ({ ...prev, userEmail: e.target.value}))}
            />
          </div>
        </div>
        <Button onClick={onClickSignUp} size="lg" color="primary" block>
          가입
        </Button>
      </div>
    );
  };
  
  export default SignUp;