import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import { isLoginState, userIdState } from "../../../recoil/RecoilState";
import AxiosInstance from "../../../api/AxiosInstance";
import { removeAccessCookieToken } from "../../../storage/Cookie";

const SignOut = () => {
    const navigate = useNavigate();

    const movePage = (url) => {
      navigate(url);
    };

    const [isLogin, setIsLogin] = useRecoilState(isLoginState);
    const [recoilUserId, setRecoilUserId] = useRecoilState(userIdState);

    const handleAxiosError = (error) => {
      setIsLogin(false);
        removeAccessCookieToken();
        movePage('/')
     }

    const SignOutUser = () => {
      AxiosInstance
      .post('/api/auth/logout')
      .then(()=> removeAccessCookieToken())
      .then(() => setIsLogin(false))
      .then(() => setRecoilUserId(null))
      .then(() => movePage('/'))
      .catch((error) => handleAxiosError(error))
    }
    useEffect(() => {
      SignOutUser();
  }, []);

    return null;
}

export default SignOut;