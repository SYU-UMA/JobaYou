import { createSlice } from '@reduxjs/toolkit';

export const TOKEN_TIME_OUT = 600*1000;

export const tokenSlice = createSlice({
    name: 'authToken',
    initialState: {
        //현재 로그인 여부 확인
        authenticated: false,
        //Access Token 저장
        accessToken: null,
        //Access Token 의 만료 시간
        expireTime: null
    },
    reducers: {
        //Access Token 정보를 저장
        SET_TOKEN: (state, action) => {
            state.authenticated = true;
            state.accessToken = action.payload;
            state.expireTime = new Date().getTime() + TOKEN_TIME_OUT;
        },
        //값을 모두 초기화함으로써 Access Token에 대한 정보도 삭제
        DELETE_TOKEN: (state) => {
            state.authenticated = false;
            state.accessToken = null;
            state.expireTime = null
        },
    }
})

export const { SET_TOKEN, DELETE_TOKEN } = tokenSlice.actions;

export default tokenSlice.reducer;