import { removeAccessCookieToken } from "../storage/Cookie";
import AxiosInstance from "./AxiosInstance";

// promise 요청 타임아웃 시간 선언
const TIME_OUT = 300*1000;

// 에러 처리를 위한 status 선언
const statusError = (status) => ({
    status: status,
    json: {
        error: ["연결이 원활하지 않습니다. 잠시 후 다시 시도해 주세요"]
    }
});

// 백으로 요청할 promise
const requestPromise = (url, option) => {
    return fetch(url, option);
};

// promise 타임아웃 처리
const timeoutPromise = () => {
    return new Promise((_, reject) => setTimeout(() => reject(new Error('timeout')), TIME_OUT));
};

// promise 요청
const getPromise = async (url, option) => {
    return await Promise.race([
                                  requestPromise(url, option),
                                  timeoutPromise()
                              ]);
};

// 백으로 로그인 요청
export const SigninUser = async (credentials) => {
    const option = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(credentials)
    };

    const data = await getPromise('/api/auth/login', option)
    .catch(() => statusError);

    if (parseInt(Number(data.status)/100)===2) {
        const status = data.ok;
        const code = data.status;
        const text = await data.text();

        return {
            status,
            code,
            text
        };
    } else {
        return statusError(data.status);
    }
};