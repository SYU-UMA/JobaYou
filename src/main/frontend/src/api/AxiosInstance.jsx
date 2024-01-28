import axios, {
  AxiosError,
  AxiosRequestConfig,
  AxiosResponse,
  InternalAxiosRequestConfig,
} from "axios";

import { getCookieAccessToken } from "../storage/Cookie";

const AxiosInstance = axios.create({
  baseURL: "/",
});

AxiosInstance.interceptors.request.use(
  (config) => {
    const access_token = getCookieAccessToken();

    if(!access_token) {
      return config;
    } else {
      config.headers["Authorization"] = `Bearer ${access_token}`;
      config.headers["Access-Control-Allow-Credentials"] = true;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default AxiosInstance;