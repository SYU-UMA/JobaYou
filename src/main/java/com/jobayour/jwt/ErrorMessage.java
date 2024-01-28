package com.jobayour.jwt;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    WRONG_TYPE_TOKEN(400, "잘못된 타입의 토큰입니다."),
    EXPIRED_TOKEN(401, "토큰이 만료되었습니다."),
    UNSUPPORTED_TOKEN(402, "지원되지 않는 토큰입니다."),
    UNKNOWN_ERROR(500, "알 수 없는 오류가 발생했습니다."),
    ACCESS_DENIED(403, "접근이 거부되었습니다.");

    private final int code;
    private final String msg;

    ErrorMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}