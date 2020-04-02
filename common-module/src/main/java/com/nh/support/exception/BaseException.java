package com.nh.support.exception;

/**
 * @Classname BaseException
 * @Description TODO
 * @Date 2020/4/2 6:13 PM
 * @Created by nihui
 */
class BaseException extends RuntimeException {
    private String ret;

    BaseException(String ret, String msg) {
        super(msg);
        this.ret = ret;
    }

    BaseException(String ret, String msg, Throwable cause) {
        super(msg, cause);
        this.ret = ret;
    }

    String getRet() {
        return ret;
    }
}
