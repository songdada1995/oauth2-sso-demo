package com.example.demo.enums;

public interface ResultCode {

    //1001xxx
    enum Common {
        UNKNOWN_ERROR(1001000),             //未知错误
        NOT_FOUND(1001001),                 //资源不存在
        ILLEGAL_ARGUMENT(1001002),          //参数非法
        INTERNAL_SERVER_ERROR(1001500),     //服务器内部错误
        ;

        public int code;

        Common(int code) {
            this.code = code;
        }
    }

    //1000xxx
    enum User {
        LOGIN_SUCCESS(1000000),             //登录成功
        LOGIN_FAILED(1000001),              //登录失败
        NOT_SIGN_IN(1000002),               //未登录或会话失效
        ;

        public int code;

        User(int code) {
            this.code = code;
        }
    }

}
