import { atom } from 'recoil';

export const interviewDataState = atom({
    key: 'interviewDataState',
    default: [],
})

export const interviewTypeState = atom({
    key: 'interviewTypeState',
    default: [],
})

export const isLoginState = atom({
    key: 'isLoginState',
    default: false
})

export const userIdState = atom({
    key: 'userIdState',
    default: null,
})