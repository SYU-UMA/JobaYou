import React, { useEffect } from "react";

import { Input, Button } from "reactstrap";

import styles from "./MyPage.module.scss";

import { Navigate, useNavigate } from "react-router-dom";

import MyPageNavigation from "../../../components/Navigation/MyPageNavigation";
import { useRecoilState } from "recoil";
import { isLoginState } from "../../../recoil/RecoilState";
import axios from "axios";
import AxiosInstance from "../../../api/AxiosInstance";
import GNB from "../../../components/GNB/GNB";
import { removeAccessCookieToken } from "../../../storage/Cookie";

const MyPage = () => {
  const navigate = useNavigate();

  const movePage = (url) => {
    navigate(url);
  };

  const [isLogin, setIsLogin] = useRecoilState(isLoginState);

  //   const isLogin = true;
  const [userData, setUserData] = React.useState({
    userId: "",
    userPassword: "",
    name: "",
    birth: "",
    phone: "",
    email: "",
  });

  const [isEdit, setIsEdit] = React.useState(false);

  const onClickEdit = () => {
    if(userData.userPassword == null || userData.name == null || userData.birth == null || userData.phone == null || userData.email == null){
      alert("빈칸이 존재합니다.");
    } else{
    setIsEdit(!isEdit);
    if (isEdit == true) {
      AxiosInstance.put("/api/auth/modify", {
        userId: userData.userId,
        userPwd: userData.userPassword,
        userName: userData.name,
        userBirthday: userData.birth,
        userPhone: userData.phone,
        userEmail: userData.email,
      })
      .catch((error) => handleAxiosError(error));
    }
  }
  };

  const userInfo = async () => {
    try{
    await AxiosInstance.get("/api/auth/info").then((response) => {
      setUserData((prev) => ({
        ...prev,
        userId: response.data.userId,
        userPassword: response.data.userPwd,
        name: response.data.userName,
        birth: response.data.userBirthday,
        phone: response.data.userPhone,
        email: response.data.userEmail,
      }));
    });}
    catch (error) {
      console.log(error);
      handleAxiosError(error);
    }
  };

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
    } else if (error.response.status === 403) {
      alert('비정상적인 접근입니다. 다시 로그인해주세요.')
      movePage('/signIn');
      setIsLogin(false);
      removeAccessCookieToken();
    }
   }

  const onChangeBirth = (e) => {
    const regex = /^[0-9]{0,8}/;
    if(regex.test(e.target.value)) {
      setUserData((prev) => ({...prev, birth: e.target.value }));
    };
  };

  const onChangePhone = (e) => {
    const regex = /^[0-9]{0,11}$/;
    if(regex.test(e.target.value)) {
      setUserData((prev) => ({...prev, phone: e.target.value }));
    }
  };

  useEffect(() => {
    if (isLogin) {
      userInfo();
    }
  }, []);
  
  if (!isLogin) {
    return <Navigate to="/signIn" replace={true} />;
  } else {
    return (
      <>
        <div className={styles.Main}>
          <GNB />
          <div className={styles.Container}>
            <h2>마이페이지</h2>
            <div className={styles.Section}>
              <div>
                <h5>아이디</h5>
                <Input
                  id="userId"
                  type="text"
                  placeholder="몇자리/ 영문,숫자, 특수문자 사용가능"
                  onChange={(e) => setUserData((prev) => ({...prev, userId: e.target.value }))}
                  value={userData.userId}
                  disabled={true}
                />
              </div>
              {isEdit == true && (
                <div>
                  <h5>비밀번호</h5>
                  <Input
                    id="userPassword"
                    type="password"
                    placeholder="몇자/ 영문, 숫자, 특수문자 조합"
                    onChange={(e) =>
                      setUserData((prev) => ({...prev, userPassword: e.target.value }))
                    }
                    value={userData.userPassword}
                    disabled={!isEdit}
                  />
                </div>
              )}
              <div>
                <h5>이름</h5>
                <Input
                  id="userName"
                  type="text"
                  placeholder="이름 입력"
                  onChange={(e) => setUserData((prev) => ({...prev, name: e.target.value }))}
                  value={userData.name}
                  disabled={!isEdit}
                />
              </div>
              <div>
                <h5>생년월일</h5>
                <Input
                  id="userBirth"
                  type="text"
                  placeholder="YYYYMMDD"
                  minLength={8}
                  maxLength={8}
                  onChange={(e) => onChangeBirth(e)}
                  value={userData.birth}
                  disabled={!isEdit}
                />
              </div>
              <div>
                <h5>휴대폰</h5>
                <Input
                  id="userPhone"
                  type="text"
                  placeholder="'-'빼고 숫자만 입력"
                  onChange={(e) => onChangePhone(e)}
                  value={userData.phone}
                  disabled={!isEdit}
                />
              </div>
              <div>
                <h5>이메일</h5>
                <Input
                  id="userEmail"
                  type="text"
                  placeholder="jobayou@jobayou.com"
                  onChange={(e) => setUserData((prev) => ({...prev, email: e.target.value }))}
                  value={userData.email}
                  disabled={!isEdit}
                />
              </div>
              <Button block onClick={onClickEdit} size="lg" color="primary">
                수정하기
              </Button>
            </div>

          </div>
        </div>
      </>
    );
  }
};

export default MyPage;
